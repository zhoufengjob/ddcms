package com.daimengshi.ddcms.utils;

import io.jboot.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

public class JsoupUtils {


    public static String getText(String html) {
        return Jsoup.parse(html).text();
    }


    static MyWhitelist whitelist = new MyWhitelist();

    public static String clear(String html) {
        if (StringUtils.isNotBlank(html))
            return Jsoup.clean(html, whitelist);

        return html;
    }

    /**
     * 做自己的白名单，允许base64的图片通过等
     *
     * @author michael
     */
    public static class MyWhitelist extends org.jsoup.safety.Whitelist {
        public MyWhitelist() {

            addTags("a", "b", "blockquote", "br", "caption", "cite", "code", "col", "colgroup", "dd", "div", "dl", "dt",
                    "em", "h1", "h2", "h3", "h4", "h5", "h6", "i", "img", "li", "ol", "p", "pre", "q", "small",
                    "strike", "strong", "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u", "ul");

            addAttributes("a", "href", "title", "target");
            addAttributes("blockquote", "cite");
            addAttributes("col", "span");
            addAttributes("colgroup", "span");
            addAttributes("img", "align", "alt", "src", "title");
            addAttributes("ol", "start");
            addAttributes("q", "cite");
            addAttributes("table", "summary");
            addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width");
            addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width");
            addAttributes("video", "src", "autoplay", "controls", "loop", "muted", "poster", "preload");

            addAttributes(":all", "class");
            addAttributes(":all", "style");
            addAttributes(":all", "height");
            addAttributes(":all", "width");
            addAttributes(":all", "type");
            addAttributes(":all", "id");
            addAttributes(":all", "name");

            addProtocols("a", "href", "ftp", "http", "https", "mailto", "tel");
            addProtocols("blockquote", "cite", "http", "https");
            addProtocols("cite", "cite", "http", "https");
            addProtocols("img", "src", "http", "https");
            addProtocols("q", "cite", "http", "https");
        }

        @Override
        protected boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
            return ("img".equals(tagName) && "src".equals(attr.getKey()) && attr.getValue().startsWith("data:;base64"))
                    || super.isSafeAttribute(tagName, el, attr);
        }
    }


}
