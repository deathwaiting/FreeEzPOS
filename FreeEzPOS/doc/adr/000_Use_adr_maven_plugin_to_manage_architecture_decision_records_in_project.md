# Use adr-maven-plugin to manage architecture decision records in project
 
* Status: accepted
* Date: 2023-07-04

# Context

Use ADR (Architecture decision records) maven plugin.
It is still in development and using it is still not the easiest, but good so far.
Still better than adding a jar to the project.

to create a new ADR run
```shell
./mvnw adr:create -Dsubject="<subject>" -DtargetPath=./doc/adr -DtemplateSourcePath=./doc/adr/templates
```

# Decision

Description of the decision.

# Consequences

Place improvements or negative impacts here.

# References

List all relevant ADRs which are related to this decision.

