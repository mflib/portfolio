@file:OptIn(InternalResourceApi::class)

package io.mohdfarhan.dev.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/io.mohdfarhan.dev.resources/"

public val Res.font.Exo2_Bold: FontResource by lazy {
      FontResource("font:Exo2_Bold", setOf(
        ResourceItem(setOf(), "${MD}font/Exo2-Bold.ttf", -1, -1),
      ))
    }

public val Res.font.Exo2_Medium: FontResource by lazy {
      FontResource("font:Exo2_Medium", setOf(
        ResourceItem(setOf(), "${MD}font/Exo2-Medium.ttf", -1, -1),
      ))
    }

public val Res.font.Exo2_Regular: FontResource by lazy {
      FontResource("font:Exo2_Regular", setOf(
        ResourceItem(setOf(), "${MD}font/Exo2-Regular.ttf", -1, -1),
      ))
    }

public val Res.font.RobotoMono_Bold: FontResource by lazy {
      FontResource("font:RobotoMono_Bold", setOf(
        ResourceItem(setOf(), "${MD}font/RobotoMono-Bold.ttf", -1, -1),
      ))
    }

public val Res.font.RobotoMono_Medium: FontResource by lazy {
      FontResource("font:RobotoMono_Medium", setOf(
        ResourceItem(setOf(), "${MD}font/RobotoMono-Medium.ttf", -1, -1),
      ))
    }

public val Res.font.RobotoMono_Regular: FontResource by lazy {
      FontResource("font:RobotoMono_Regular", setOf(
        ResourceItem(setOf(), "${MD}font/RobotoMono-Regular.ttf", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectWebMainFont0Resources(map: MutableMap<String, FontResource>) {
  map.put("Exo2_Bold", Res.font.Exo2_Bold)
  map.put("Exo2_Medium", Res.font.Exo2_Medium)
  map.put("Exo2_Regular", Res.font.Exo2_Regular)
  map.put("RobotoMono_Bold", Res.font.RobotoMono_Bold)
  map.put("RobotoMono_Medium", Res.font.RobotoMono_Medium)
  map.put("RobotoMono_Regular", Res.font.RobotoMono_Regular)
}
