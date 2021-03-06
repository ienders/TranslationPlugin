package cn.yiiguxing.plugin.translate.trans

/**
 * 语言
 */
enum class Lang(val langName: String, val code: String) {
    AUTO("Auto", "auto"),
    CHINESE("Chinese", "zh-CN"),
    ENGLISH("English", "en"),
    CHINESE_TRADITIONAL("Chinese (Traditional)", "zh-TW"),
    ALBANIAN("Albanian", "sq"),
    ARABIC("Arabic", "ar"),
    AMHARIC("Amharic", "am"),
    AZERBAIJANI("Azerbaijani", "az"),
    IRISH("Irish", "ga"),
    ESTONIAN("Estonian", "et"),
    BASQUE("Basque", "eu"),
    BELARUSIAN("Belarusian", "be"),
    BULGARIAN("Bulgarian", "bg"),
    ICELANDIC("Icelandic", "is"),
    POLISH("Polish", "pl"),
    BOSNIAN("Bosnian", "bs"),
    PERSIAN("Persian", "fa"),
    AFRIKAANS("Afrikaans", "af"),
    DANISH("Danish", "da"),
    GERMAN("German", "de"),
    RUSSIAN("Russian", "ru"),
    FRENCH("French", "fr"),
    FILIPINO("Filipino", "tl"),
    FINNISH("Finnish", "fi"),
    FRISIAN("Frisian", "fy"),
    KHMER("Khmer", "km"),
    GEORGIAN("Georgian", "ka"),
    GUJARATI("Gujarati", "gu"),
    KAZAKH("Kazakh", "kk"),
    HAITIAN_CREOLE("Haitian_creole", "ht"),
    KOREAN("Korean", "ko"),
    HAUSA("Hausa", "ha"),
    DUTCH("Dutch", "nl"),
    KYRGYZ("Kyrgyz", "ky"),
    GALICIAN("Galician", "gl"),
    CATALAN("Catalan", "ca"),
    CZECH("Czech", "cs"),
    KANNADA("Kannada", "kn"),
    CORSICAN("Corsican", "co"),
    CROATIAN("Croatian", "hr"),
    KURDISH("Kurdish", "ku"),
    LATIN("Latin", "la"),
    LATVIAN("Latvian", "lv"),
    LAO("Lao", "lo"),
    LITHUANIAN("Lithuanian", "lt"),
    LUXEMBOURGISH("Luxembourgish", "lb"),
    ROMANIAN("Romanian", "ro"),
    MALAGASY("Malagasy", "mg"),
    MALTESE("Maltese", "mt"),
    MARATHI("Marathi", "mr"),
    MALAYALAM("Malayalam", "ml"),
    MALAY("Malay", "ms"),
    MACEDONIAN("Macedonian", "mk"),
    MAORI("Maori", "mi"),
    MONGOLIAN("Mongolian", "mn"),
    BENGALI("Bengali", "bn"),
    MYANMAR("Myanmar", "my"),
    HMONG("Hmong", "hmn"),
    XHOSA("Xhosa", "xh"),
    ZULU("Zulu", "zu"),
    NEPALI("Nepali", "ne"),
    NORWEGIAN("Norwegian", "no"),
    PUNJABI("Punjabi", "pa"),
    PORTUGUESE("Portuguese", "pt"),
    PASHTO("Pashto", "ps"),
    CHICHEWA("Chichewa", "ny"),
    JAPANESE("Japanese", "ja"),
    SWEDISH("Swedish", "sv"),
    SAMOAN("Samoan", "sm"),
    SERBIAN("Serbian", "sr"),
    SESOTHO("Sesotho", "st"),
    SINHALA("Sinhala", "si"),
    ESPERANTO("Esperanto", "eo"),
    SLOVAK("Slovak", "sk"),
    SLOVENIAN("Slovenian", "sl"),
    SWAHILI("Swahili", "sw"),
    SCOTS_GAELIC("Scots_gaelic", "gd"),
    CEBUANO("Cebuano", "ceb"),
    SOMALI("Somali", "so"),
    TAJIK("Tajik", "tg"),
    TELUGU("Telugu", "te"),
    TAMIL("Tamil", "ta"),
    THAI("Thai", "th"),
    TURKISH("Turkish", "tr"),
    WELSH("Welsh", "cy"),
    URDU("Urdu", "ur"),
    UKRAINIAN("Ukrainian", "uk"),
    UZBEK("Uzbek", "uz"),
    SPANISH("Spanish", "es"),
    HEBREW("Hebrew", "iw"),
    GREEK("Greek", "el"),
    HAWAIIAN("Hawaiian", "haw"),
    SINDHI("Sindhi", "sd"),
    HUNGARIAN("Hungarian", "hu"),
    SHONA("Shona", "sn"),
    ARMENIAN("Armenian", "hy"),
    IGBO("Igbo", "ig"),
    ITALIAN("Italian", "it"),
    YIDDISH("Yiddish", "yi"),
    HINDI("Hindi", "hi"),
    SUNDANESE("Sundanese", "su"),
    INDONESIAN("Indonesian", "id"),
    JAVANESE("Javanese", "jw"),
    YORUBA("Yoruba", "yo"),
    VIETNAMESE("Vietnamese", "vi");

    companion object {
        fun valueOfCode(code: String): Lang = when (code) {
            "zh-CHS" -> CHINESE
            else -> values().find { it.code.equals(code, ignoreCase = true) }
                    ?: throw IllegalArgumentException("Unknown language " + "code:$code")
        }
    }
}
