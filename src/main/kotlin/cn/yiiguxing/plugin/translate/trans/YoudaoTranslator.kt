package cn.yiiguxing.plugin.translate.trans

import cn.yiiguxing.plugin.translate.LINK_SETTINGS
import cn.yiiguxing.plugin.translate.YOUDAO_TRANSLATE_URL
import cn.yiiguxing.plugin.translate.ui.icon.Icons
import cn.yiiguxing.plugin.translate.util.Settings
import cn.yiiguxing.plugin.translate.util.i
import cn.yiiguxing.plugin.translate.util.md5
import cn.yiiguxing.plugin.translate.util.urlEncode
import com.google.gson.Gson
import com.intellij.openapi.diagnostic.Logger
import javax.swing.Icon

object YoudaoTranslator : AbstractTranslator() {

    const val TRANSLATOR_ID = "ai.youdao"

    private const val TRANSLATOR_NAME = "Youdao Translate"

    val SUPPORTED_LANGUAGES: List<Lang> = listOf(
            Lang.AUTO,
            Lang.CHINESE,
            Lang.ENGLISH,
            Lang.JAPANESE,
            Lang.KOREAN,
            Lang.FRENCH,
            Lang.RUSSIAN,
            Lang.PORTUGUESE,
            Lang.SPANISH)

    private val logger: Logger = Logger.getInstance(YoudaoTranslator::class.java)

    override val id: String = TRANSLATOR_ID

    override val name: String = TRANSLATOR_NAME

    override val icon: Icon = Icons.Youdao

    override val primaryLanguage: Lang
        get() = Settings.youdaoTranslateSettings.primaryLanguage

    override val supportedSourceLanguages: List<Lang> = SUPPORTED_LANGUAGES
    override val supportedTargetLanguages: List<Lang> = SUPPORTED_LANGUAGES

    override fun getTranslateUrl(text: String, srcLang: Lang, targetLang: Lang): String {
        val settings = Settings.youdaoTranslateSettings
        val appId = settings.appId
        val privateKey = settings.getAppKey()
        val salt = System.currentTimeMillis().toString()
        val sign = (appId + text + salt + privateKey).md5()

        return (YOUDAO_TRANSLATE_URL +
                "?appKey=${appId.urlEncode()}" +
                "&from=${getLanguageCode(srcLang)}" +
                "&to=${getLanguageCode(targetLang)}" +
                "&salt=$salt" +
                "&sign=$sign" +
                "&q=${text.urlEncode()}")
                .also {
                    logger.i("Translate url: $it")
                }
    }

    private fun getLanguageCode(lang: Lang): String = if (lang == Lang.CHINESE) "zh-CHS" else lang.code

    override fun parserResult(original: String, srcLang: Lang, targetLang: Lang, result: String): Translation {
        logger.i("Translate result: $result")

        return Gson().fromJson(result, YoudaoTranslation::class.java).apply {
            query = original
            checkError()
            if (!isSuccessful) {
                throw YoudaoTranslateException(errorCode)
            }
        }.toTranslation()
    }

    override fun createErrorMessage(throwable: Throwable): String = when (throwable) {
        is YoudaoTranslateException -> "错误[${throwable.code}]: " + when (throwable.code) {
            101 -> "Missing required parameters"
            102 -> "Unsupported language type"
            103 -> "Translated text is too long"
            104 -> "Unsupported API type"
            105 -> "Unsupported signature type"
            106 -> "Unsupported response type"
            107 -> "Unsupported transfer encryption type"
            108 -> "Invalid AppKey - $LINK_SETTINGS"
            109 -> "BatchLog format is incorrect"
            110 -> "No valid examples of related services"
            111 -> "Invalid account - $LINK_SETTINGS"
            201 -> "Decryption failed"
            202 -> "Signature test failed - $LINK_SETTINGS"
            203 -> "The access IP address is not in the accessible IP list"
            301 -> "Dictionary query failed"
            302 -> "Translation query failed"
            303 -> "Abnormal server"
            401 -> "Account is in arrears"
            else -> "Unknown Error"
        }
        else -> super.createErrorMessage(throwable)
    }

    private class YoudaoTranslateException(val code: Int) : TranslateException("Translate failed: $code")

}
