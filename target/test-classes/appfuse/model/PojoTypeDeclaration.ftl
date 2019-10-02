<#include "Ejb3TypeDeclaration.ftl"/>

@XmlRootElement
${pojo.getClassModifiers()} ${pojo.getDeclarationType()} ${pojo.getDeclarationName()} extends BaseObject implements Serializable