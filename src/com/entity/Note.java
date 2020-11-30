/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: note
 * Author:   陈伟林
 * Date:     2019/2/24 23:56
 * Description: 记事本
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.entity;

/**
 * 〈一句话功能简述〉<br> 
 * 〈记事本〉
 *
 * @author 陈伟林
 * @create 2019/2/24
 * @since 1.0.0
 */
public class Note {
    private int note_id;
    private String subject;
    private String content;
    private int cate_id;
    private String date;

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}