package io.github.eternalpro.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import io.github.gefangshuai.plugin.model.core.WModel;

/**
 * Created by gefangshuai on 2015/7/7.
 */
@TableBind(pkName = "id", tableName = "book")
public class Book extends WModel<Book>{
    public static final Book dao = new Book();
}
