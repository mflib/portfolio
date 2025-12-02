package io.mohdfarhan.dev.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.mohdfarhan.dev.resources.Exo2_Bold
import io.mohdfarhan.dev.resources.Exo2_Medium
import io.mohdfarhan.dev.resources.Exo2_Regular
import io.mohdfarhan.dev.resources.Res
import io.mohdfarhan.dev.resources.RobotoMono_Bold
import io.mohdfarhan.dev.resources.RobotoMono_Medium
import io.mohdfarhan.dev.resources.RobotoMono_Regular
import org.jetbrains.compose.resources.Font



@Composable
fun bodyFontFamily() = FontFamily(
    Font(Res.font.RobotoMono_Regular, FontWeight.Normal),
    Font(Res.font.RobotoMono_Medium, FontWeight.Medium),
    Font(Res.font.RobotoMono_Bold, FontWeight.Bold)
)

@Composable
fun displayFontFamily() = FontFamily(
    Font(Res.font.Exo2_Regular, FontWeight.Normal),
    Font(Res.font.Exo2_Medium, FontWeight.Medium),
    Font(Res.font.Exo2_Bold, FontWeight.Bold)
)


val baseline = Typography()
@get:Composable
val AppTypography: Typography
    get() = Typography(
        displayLarge   = baseline.displayLarge.copy(fontFamily   = displayFontFamily()),
        displayMedium  = baseline.displayMedium.copy(fontFamily  = displayFontFamily()),
        displaySmall   = baseline.displaySmall.copy(fontFamily   = displayFontFamily()),
        headlineLarge  = baseline.headlineLarge.copy(fontFamily  = displayFontFamily()),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily()),
        headlineSmall  = baseline.headlineSmall.copy(fontFamily  = displayFontFamily()),
        titleLarge     = baseline.titleLarge.copy(fontFamily     = displayFontFamily()),
        titleMedium    = baseline.titleMedium.copy(fontFamily    = displayFontFamily()),
        titleSmall     = baseline.titleSmall.copy(fontFamily     = displayFontFamily()),
        bodyLarge      = baseline.bodyLarge.copy(fontFamily      = bodyFontFamily()),
        bodyMedium     = baseline.bodyMedium.copy(fontFamily     = bodyFontFamily()),
        bodySmall      = baseline.bodySmall.copy(fontFamily      = bodyFontFamily()),
        labelLarge     = baseline.labelLarge.copy(fontFamily     = bodyFontFamily()),
        labelMedium    = baseline.labelMedium.copy(fontFamily    = bodyFontFamily()),
        labelSmall     = baseline.labelSmall.copy(fontFamily     = bodyFontFamily()),
    )
