# http://www.gnu.org/software/make/manual/make.html
# https://www.gnu.org/prep/standards/html_node/Makefile-Basics.html#Makefile-Basics
# http://clarkgrubb.com/makefile-style-guide

############
# PROLOGUE #
############
MAKEFLAGS += --warn-undefined-variables
SHELL = /bin/sh
.SHELLFLAGS := -eu -o pipefail -c
.DEFAULT_GOAL := all
.DELETE_ON_ERROR:
.SUFFIXES:

######################
# INTERNAL VARIABLES #
######################
TIMESTAMP := $(shell /bin/date "+%Y%m%d%H%M%S")
CURRENT_YEAR := $(shell /bin/date "+%Y")
USERID := $(shell id -u)
GREEN  := $(shell tput -Txterm setaf 2)
WHITE  := $(shell tput -Txterm setaf 7)
YELLOW := $(shell tput -Txterm setaf 3)
RESET  := $(shell tput -Txterm sgr0)

######################
# INTERNAL FUNCTIONS #
######################
HELP_FUN = \
    %help; \
    while(<>) { push @{$$help{$$2 // 'targets'}}, [$$1, $$3] if /^([a-zA-Z\-]+)\s*:.*\#\#(?:@([a-zA-Z\-]+))?\s(.*)$$/ }; \
    print "usage: make [target]\n\n"; \
    for (sort keys %help) { \
    print "${WHITE}$$_:${RESET}\n"; \
    for (@{$$help{$$_}}) { \
    $$sep = " " x (32 - length $$_->[0]); \
    print "  ${YELLOW}$$_->[0]${RESET}$$sep${GREEN}$$_->[1]${RESET}\n"; \
    }; \
    print "\n"; }

###############
# GOALS/RULES #
###############
.PHONY: all
all: help

help: ##@other Show this help
	@perl -e '$(HELP_FUN)' $(MAKEFILE_LIST)

.PHONY: sonar-analysis
sonar-analysis: ##@sebhoss Perform Sonarqube analysis
	# http://docs.sonarqube.org/display/SONAR/Analyzing+with+Maven
	mvn clean install
	mvn sonar:sonar -Dsonar.host.url=http://localhost:59000 -Dsonar.pitest.mode=reuseReport

.PHONY: sign-waiver
sign-waiver: ##@contributing Sign the WAIVER
	gpg2 --no-version --armor --sign AUTHORS/WAIVER

.PHONY: docker-verify
docker-verify: ##@docker Verify project in pre-defined build environment
	docker-compose -f build/docker/build-environment.yml run --rm verify-project
	# since we are 'root' inside the container, we need another container run to cleanup after ourselves
	docker-compose -f build/docker/build-environment.yml run --rm clean-project
	# findbugs likes to create these
	rm -rf ?/

.PHONY: update-parent
update-parent: ##@maintenance Updates the Maven parent to its latest version
	mvn versions:update-parent -U -DgenerateBackupPoms=false
	git add pom.xml
	git commit pom.xml -s -S -m 'Update to latest parent'

.PHONY: update-maintenance-badge
update-maintenance-badge: ##@maintenance Updates the maintenance badge to the current year
	sed -i -e "s/maintenance\/yes\/[0-9]*/maintenance\/yes\/$(CURRENT_YEAR)/" README.asciidoc
	git add README.asciidoc
	git commit pom.xml -s -S -m 'Update maintenance badge'

.PHONY: release-into-local-nexus
release-into-local-nexus: ##@release Release all artifacts into a local nexus
	mvn clean deploy scm:tag -Prelease -Drevision=$(TIMESTAMP) -DpushChanges=false -DskipLocalStaging=true -Drelease=local

.PHONY: release-into-sonatype-nexus
release-into-sonatype-nexus: ##@release Release all artifacts into Maven Central (through Sonatype OSSRH)
	mvn clean deploy scm:tag -Prelease -Drevision=$(TIMESTAMP) -DpushChanges=false -Drelease=sonatype
	git push --tags origin master
