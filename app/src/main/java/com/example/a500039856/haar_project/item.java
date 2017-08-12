package com.example.a500039856.haar_project;

/**
 * Created by This Pc on 16-03-2017.
 */
public class item {
    String title;
    String link;
    String description;
    String Price;
    String imglink;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        int position=title.indexOf(" ");
        this.title = title.substring(position+1);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        int position=link.indexOf(" ");
        this.link = link.substring(position+1);
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return Price;
    }

    public String getImglink() {
        return imglink;
    }

    public void setDescription(String description) {
        int start=description.indexOf("<font color=\"#990000\">");
        int end=description.indexOf("</font>");
        String temp=description.substring(start,end);
        start=temp.indexOf("Rs.");
        temp=temp.substring(start);
        start=temp.indexOf("</span>");
        end=temp.indexOf("</span>", start + 7);
        Price=temp.substring(start + 7, end);
        start=description.indexOf("<img src=\"");
        end=description.indexOf(".jpg");
        imglink=description.substring(start+10,end+4);
        this.description = description;
    }
}
