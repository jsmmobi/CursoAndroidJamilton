package br.com.bitocean.whatsappclone.model;

import java.io.Serializable;

public class Mensagem implements Serializable {
    public static final String NODE="Mensagem";
    private String objectId;
    private String idSender;
    private String idClient;
    private String textMessage;
    private String imageMessageUrl;
    private String videoMessageUrl;
    private String urlMessage;


    public Mensagem() {
    }

    public String getObjectId() {
        return objectId;
    }

    public Mensagem setObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getIdSender() {
        return idSender;
    }

    public Mensagem setIdSender(String idSender) {
        this.idSender = idSender;
        return this;
    }

    public String getIdClient() {
        return idClient;
    }

    public Mensagem setIdClient(String idClient) {
        this.idClient = idClient;
        return this;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public Mensagem setTextMessage(String textMessage) {
        this.textMessage = textMessage;
        return this;
    }

    public String getImageMessageUrl() {
        return imageMessageUrl;
    }

    public Mensagem setImageMessageUrl(String imageMessageUrl) {
        this.imageMessageUrl = imageMessageUrl;
        return this;
    }

    public String getVideoMessageUrl() {
        return videoMessageUrl;
    }

    public Mensagem setVideoMessageUrl(String videoMessageUrl) {
        this.videoMessageUrl = videoMessageUrl;
        return this;
    }

    public String getUrlMessage() {
        return urlMessage;
    }

    public Mensagem setUrlMessage(String urlMessage) {
        this.urlMessage = urlMessage;
        return this;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "objectId='" + objectId + '\'' +
                ", idSender='" + idSender + '\'' +
                ", idClient='" + idClient + '\'' +
                ", textMessage='" + textMessage + '\'' +
                ", imageMessageUrl='" + imageMessageUrl + '\'' +
                ", videoMessageUrl='" + videoMessageUrl + '\'' +
                ", urlMessage='" + urlMessage + '\'' +
                '}';
    }
}
