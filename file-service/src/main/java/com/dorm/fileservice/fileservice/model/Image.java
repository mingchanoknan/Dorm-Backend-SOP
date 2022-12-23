package com.dorm.fileservice.fileservice.model;

import lombok.Data;

@Data
public class Image {
    private String id;
    private String title;
    private String description;
    private String dateTime;
    private String type;
    private String animate;
    private String width;
    private String height;
    private String size;
    private String views;
    private String bandwidth;
    private String vote;
    private String favorite;
    private String nsfw;
    private String section;
    private String account_url;
    private String account_id;
    private String is_ad;
    private String in_most_viral;
    private String has_sound;
    private String link;
}
