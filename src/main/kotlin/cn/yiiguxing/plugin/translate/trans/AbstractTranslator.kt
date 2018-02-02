package cn.yiiguxing.plugin.translate.trans

import com.google.gson.JsonSyntaxException
import com.intellij.util.io.HttpRequests
import com.intellij.util.io.RequestBuilder
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException

/**
 * AbstractTranslator
 *
 * Created by Yii.Guxing on 2017-10-29 0029.
 */
abstract class AbstractTranslator : Translator {

    protected abstract fun getTranslateUrl(text: String, srcLang: Lang, targetLang: Lang): String

    protected open fun buildRequest(builder: RequestBuilder) {}

    protected abstract fun parserResult(original: String, srcLang: Lang, targetLang: Lang, result: String): Translation

    protected open fun createErrorMessage(throwable: Throwable): String = when (throwable) {
        is UnsupportedLanguageException -> "Unsupported language: ${throwable.lang.langName}"
        is SocketException -> "Network Error"
        is ConnectException -> "Connect Error"
        is SocketTimeoutException -> "Socket Timeout"
        is JsonSyntaxException -> "Parse Error"
        else -> "Translation failed: Unknown error"
    }

    override fun translate(text: String, srcLang: Lang, targetLang: Lang): Translation = try {
        if (srcLang !in supportedSourceLanguages) {
            throw UnsupportedLanguageException(srcLang)
        }
        if (targetLang !in supportedTargetLanguages) {
            throw UnsupportedLanguageException(targetLang)
        }

        HttpRequests.request(getTranslateUrl(text, srcLang, targetLang))
                .also { buildRequest(it) }
                .connect {
                    parserResult(text, srcLang, targetLang, it.readString(null))
                }
    } catch (throwable: Throwable) {
        throw TranslateException(createErrorMessage(throwable), throwable)
    }
}