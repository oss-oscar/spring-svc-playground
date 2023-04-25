SHELL := bash
gradle_bare := ./gradlew
gradle := $(gradle_bare)

run:
	$(gradle) bootRun --args='--spring.profiles.active=local'

test:
	$(gradle) test

styleCheck:
	$(gradle) ktlintCheck

styleApply:
	$(gradle) ktlintFormat

imageBuild:
	$(gradle) jib --image=oscarcpozas/svc-kotlin-playground