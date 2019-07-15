package com.fnhelper.fnapp.data;

public class PhoneBean {


    /**
     * id : 1
     * phoneId : 1
     * phoneName : 一加手机7 Pro
     * sellPoint : 大屏手机,大容量电池,快充手机,光学防抖,2.5D弧面屏
     * prize : 3999
     * timeToMarket : 2019-05-22
     * phoneSize : 162.6×75.9×8.8mm
     * phoneScreenSize : 6.67英寸
     * screenResolution : 3120x1440像素
     * RearCamera : 4800+1600+800万像素
     * FrontCamera : 1600万像素
     * os : Android 9.0
     * cpu : 高通骁龙855
     * ram : 6gb
     * rom : 128gb
     * pics : http://imgm6.cnmo-img.com.cn/handimg/2019/05/171604661g6emmyjocobcg.jpg,http://imgm.cnmo-img.com.cn/cnmo_product/23/660/cen53wm4sFK4Y.jpg,http://imgm3.cnmo-img.com.cn/cnmo_product/23/663/cenDWEeyNh6E.jpg
     * RelatedArticles : 放眼全球 一加7 Pro 90Hz流体屏受到海外媒体一致好评,http://smartcar.cnmo.com/news/665317.html
     */

    private String id;
    private String pic;
    private String phoneId;
    private String phoneName;
    private String sellPoint;
    private String prize;
    private String timeToMarket;
    private String phoneSize;
    private String phoneScreenSize;
    private String screenResolution;
    private String RearCamera;
    private String FrontCamera;
    private String os;
    private String cpu;
    private String ram;
    private String rom;
    private String pics;
    private String RelatedArticles;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getTimeToMarket() {
        return timeToMarket;
    }

    public void setTimeToMarket(String timeToMarket) {
        this.timeToMarket = timeToMarket;
    }

    public String getPhoneSize() {
        return phoneSize;
    }

    public void setPhoneSize(String phoneSize) {
        this.phoneSize = phoneSize;
    }

    public String getPhoneScreenSize() {
        return phoneScreenSize;
    }

    public void setPhoneScreenSize(String phoneScreenSize) {
        this.phoneScreenSize = phoneScreenSize;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getRearCamera() {
        return RearCamera;
    }

    public void setRearCamera(String RearCamera) {
        this.RearCamera = RearCamera;
    }

    public String getFrontCamera() {
        return FrontCamera;
    }

    public void setFrontCamera(String FrontCamera) {
        this.FrontCamera = FrontCamera;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getRelatedArticles() {
        return RelatedArticles;
    }

    public void setRelatedArticles(String RelatedArticles) {
        this.RelatedArticles = RelatedArticles;
    }

    @Override
    public String toString() {
        return "PhoneBean{" + "id='" + id + '\'' +"pic='" + pic + '\'' + ", phoneId='" + phoneId + '\'' + ", phoneName='" + phoneName + '\'' + ", sellPoint='" + sellPoint + '\'' + ", prize='" + prize + '\'' + ", timeToMarket='" + timeToMarket + '\'' + ", phoneSize='" + phoneSize + '\'' + ", phoneScreenSize='" + phoneScreenSize + '\'' + ", screenResolution='" + screenResolution + '\'' + ", RearCamera='" + RearCamera + '\'' + ", FrontCamera='" + FrontCamera + '\'' + ", os='" + os + '\'' + ", cpu='" + cpu + '\'' + ", ram='" + ram + '\'' + ", rom='" + rom + '\'' + ", pics='" + pics + '\'' + ", RelatedArticles='" + RelatedArticles + '\'' + '}';
    }
}
