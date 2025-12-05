package io.mohdfarhan.dev.data

import kotlinx.serialization.Serializable

@Serializable
data class Hero(
    val name: String,
    val title: String,
    val aboutMe: String
)

@Serializable
data class SkillItem(
    val icon: String,
    val title: String,
    val description: String
)

@Serializable
data class Skill(
    val title: String,
    val subtitle: String,
    val items: List<SkillItem>
)

@Serializable
data class ExperienceItem(
    val title: String,
    val company: String,
    val period: String,
    val description: List<String>,
    val isRightAligned: Boolean
)

@Serializable
data class Experience(
    val title: String,
    val subtitle: String,
    val items: List<ExperienceItem>
)

@Serializable
data class OpenSourceItem(
    val title: String,
    val subtitle: String,
    val description: String,
    val stars: Int,
    val tags: String,
    val githubLink: String
)

@Serializable
data class OpenSource(
    val title: String,
    val subtitle: String,
    val items: List<OpenSourceItem>
)

@Serializable
data class Footer(
    val title: String,
    val subtitle: String
)

@Serializable
data class PortfolioData(
    val hero: Hero,
    val skill: Skill,
    val experience: Experience,
    val openSource: OpenSource,
    val footer: Footer
)
