@file:OptIn(InternalResourceApi::class)

package io.mohdfarhan.dev.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/io.mohdfarhan.dev.resources/"

public val Res.drawable.browser_code: DrawableResource by lazy {
      DrawableResource("drawable:browser_code", setOf(
        ResourceItem(setOf(), "${MD}drawable/browser-code.svg", -1, -1),
      ))
    }

public val Res.drawable.cloud: DrawableResource by lazy {
      DrawableResource("drawable:cloud", setOf(
        ResourceItem(setOf(), "${MD}drawable/cloud.svg", -1, -1),
      ))
    }

public val Res.drawable.compose_multiplatform: DrawableResource by lazy {
      DrawableResource("drawable:compose_multiplatform", setOf(
        ResourceItem(setOf(), "${MD}drawable/compose-multiplatform.xml", -1, -1),
      ))
    }

public val Res.drawable.database: DrawableResource by lazy {
      DrawableResource("drawable:database", setOf(
        ResourceItem(setOf(), "${MD}drawable/database.svg", -1, -1),
      ))
    }

public val Res.drawable.instagram: DrawableResource by lazy {
      DrawableResource("drawable:instagram", setOf(
        ResourceItem(setOf(), "${MD}drawable/instagram.svg", -1, -1),
      ))
    }

public val Res.drawable.iphone: DrawableResource by lazy {
      DrawableResource("drawable:iphone", setOf(
        ResourceItem(setOf(), "${MD}drawable/iphone.svg", -1, -1),
      ))
    }

public val Res.drawable.learn: DrawableResource by lazy {
      DrawableResource("drawable:learn", setOf(
        ResourceItem(setOf(), "${MD}drawable/learn.svg", -1, -1),
      ))
    }

public val Res.drawable.linkedin: DrawableResource by lazy {
      DrawableResource("drawable:linkedin", setOf(
        ResourceItem(setOf(), "${MD}drawable/linkedin.svg", -1, -1),
      ))
    }

public val Res.drawable.merge: DrawableResource by lazy {
      DrawableResource("drawable:merge", setOf(
        ResourceItem(setOf(), "${MD}drawable/merge.svg", -1, -1),
      ))
    }

public val Res.drawable.twitter: DrawableResource by lazy {
      DrawableResource("drawable:twitter", setOf(
        ResourceItem(setOf(), "${MD}drawable/twitter.svg", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectWebMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("browser_code", Res.drawable.browser_code)
  map.put("cloud", Res.drawable.cloud)
  map.put("compose_multiplatform", Res.drawable.compose_multiplatform)
  map.put("database", Res.drawable.database)
  map.put("instagram", Res.drawable.instagram)
  map.put("iphone", Res.drawable.iphone)
  map.put("learn", Res.drawable.learn)
  map.put("linkedin", Res.drawable.linkedin)
  map.put("merge", Res.drawable.merge)
  map.put("twitter", Res.drawable.twitter)
}
