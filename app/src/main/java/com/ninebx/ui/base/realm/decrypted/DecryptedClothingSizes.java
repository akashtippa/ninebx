package com.ninebx.ui.base.realm.decrypted;



import android.os.Parcel;
import android.os.Parcelable;

import com.ninebx.ui.base.realm.RealmString;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Alok on 29/01/18.
 */
public class DecryptedClothingSizes implements Parcelable {

    @PrimaryKey //@Required
    private int id = 0;

    @Required
    private RealmList<RealmString> backingImages = new RealmList<>();

    @Ignore
    @Required private List<String> photosId = new ArrayList<>();

    protected DecryptedClothingSizes(Parcel in) {
        id = in.readInt();
        photosId = in.createStringArrayList();
        selectionType = in.readString();
        classType = in.readString();
        personName = in.readString();
        sizeName = in.readString();
        sizeCategory = in.readString();
        topsSize = in.readString();
        topsNumericSize = in.readString();
        bottomsSize = in.readString();
        bottomsNumericSize = in.readString();
        dressesSize = in.readString();
        dressesNumericSize = in.readString();
        outWearSize = in.readString();
        outWearNumericSize = in.readString();
        swimWearSize = in.readString();
        swimWearNumericSize = in.readString();
        swimWearBraBandCupSize = in.readString();
        shoeSize = in.readString();
        shoeWidth = in.readString();
        hats = in.readString();
        gloves = in.readString();
        tights = in.readString();
        bust = in.readString();
        waist = in.readString();
        hips = in.readString();
        armLength = in.readString();
        inseam = in.readString();
        torso = in.readString();
        jacketsSize = in.readString();
        jacketsNumericSize = in.readString();
        pantsSize = in.readString();
        pantsNumericSize = in.readString();
        toddlerSize = in.readString();
        kidSize = in.readString();
        neck = in.readString();
        chest = in.readString();
        clothing = in.readString();
        shoes = in.readString();
        notes = in.readString();
        attachmentNames = in.readString();
        byte tmpIsBaby = in.readByte();
        isBaby = tmpIsBaby == 0 ? null : tmpIsBaby == 1;
        byte tmpIsWomen = in.readByte();
        isWomen = tmpIsWomen == 0 ? null : tmpIsWomen == 1;
        byte tmpIsGirl = in.readByte();
        isGirl = tmpIsGirl == 0 ? null : tmpIsGirl == 1;
        byte tmpIsMen = in.readByte();
        isMen = tmpIsMen == 0 ? null : tmpIsMen == 1;
        byte tmpIsBoy = in.readByte();
        isBoy = tmpIsBoy == 0 ? null : tmpIsBoy == 1;
        beltsNumericSize = in.readString();
        socks = in.readString();
        seat = in.readString();
        beltSize = in.readString();
        created = in.readString();
        modified = in.readString();
        byte tmpIsPrivate = in.readByte();
        isPrivate = tmpIsPrivate == 0 ? null : tmpIsPrivate == 1;
        createdUser = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(photosId);
        dest.writeString(selectionType);
        dest.writeString(classType);
        dest.writeString(personName);
        dest.writeString(sizeName);
        dest.writeString(sizeCategory);
        dest.writeString(topsSize);
        dest.writeString(topsNumericSize);
        dest.writeString(bottomsSize);
        dest.writeString(bottomsNumericSize);
        dest.writeString(dressesSize);
        dest.writeString(dressesNumericSize);
        dest.writeString(outWearSize);
        dest.writeString(outWearNumericSize);
        dest.writeString(swimWearSize);
        dest.writeString(swimWearNumericSize);
        dest.writeString(swimWearBraBandCupSize);
        dest.writeString(shoeSize);
        dest.writeString(shoeWidth);
        dest.writeString(hats);
        dest.writeString(gloves);
        dest.writeString(tights);
        dest.writeString(bust);
        dest.writeString(waist);
        dest.writeString(hips);
        dest.writeString(armLength);
        dest.writeString(inseam);
        dest.writeString(torso);
        dest.writeString(jacketsSize);
        dest.writeString(jacketsNumericSize);
        dest.writeString(pantsSize);
        dest.writeString(pantsNumericSize);
        dest.writeString(toddlerSize);
        dest.writeString(kidSize);
        dest.writeString(neck);
        dest.writeString(chest);
        dest.writeString(clothing);
        dest.writeString(shoes);
        dest.writeString(notes);
        dest.writeString(attachmentNames);
        dest.writeByte((byte) (isBaby == null ? 0 : isBaby ? 1 : 2));
        dest.writeByte((byte) (isWomen == null ? 0 : isWomen ? 1 : 2));
        dest.writeByte((byte) (isGirl == null ? 0 : isGirl ? 1 : 2));
        dest.writeByte((byte) (isMen == null ? 0 : isMen ? 1 : 2));
        dest.writeByte((byte) (isBoy == null ? 0 : isBoy ? 1 : 2));
        dest.writeString(beltsNumericSize);
        dest.writeString(socks);
        dest.writeString(seat);
        dest.writeString(beltSize);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeByte((byte) (isPrivate == null ? 0 : isPrivate ? 1 : 2));
        dest.writeString(createdUser);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DecryptedClothingSizes> CREATOR = new Creator<DecryptedClothingSizes>() {
        @Override
        public DecryptedClothingSizes createFromParcel(Parcel in) {
            return new DecryptedClothingSizes(in);
        }

        @Override
        public DecryptedClothingSizes[] newArray(int size) {
            return new DecryptedClothingSizes[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<RealmString> getBackingImages() {
        return backingImages;
    }

    public void setBackingImages(RealmList<RealmString> backingImages) {
        this.backingImages = backingImages;
    }

    public List<String> getPhotosId() {
        photosId = new ArrayList<>();
        for( RealmString realmString : backingImages ) {
            photosId.add( realmString.getStringValue() );
        }
        return photosId;
    }

    public void setPhotosId(List<String> photosId) {
        this.photosId = photosId;
        backingImages.clear();
        for( String string : photosId ) {
            backingImages.add( new RealmString(string) );
        }
    }
    @Required private String selectionType = "";
    @Required private String classType = "";

    @Required private String personName = "";
    @Required private String sizeName = "";

    @Required private String sizeCategory = "";

    @Required private String topsSize = "";
    @Required private String topsNumericSize = "";

    @Required private String bottomsSize = "";
    @Required private String bottomsNumericSize = "";

    @Required private String dressesSize = "";
    @Required private String dressesNumericSize = "";

    @Required private String outWearSize = "";
    @Required private String outWearNumericSize = "";

    @Required private String swimWearSize = "";
    @Required private String swimWearNumericSize = "";
    @Required private String swimWearBraBandCupSize = "";

    @Required private String shoeSize = "";
    @Required private String shoeWidth = "";

    @Required private String hats = "";
    @Required private String gloves = "";
    @Required private String tights = "";

    @Required private String bust = "";

    @Required private String waist = "";

    @Required private String hips = "";
    @Required private String armLength = "";

    @Required private String inseam = "";
    @Required private String torso = "";

    // MEN
    @Required private String jacketsSize = "";
    @Required private String jacketsNumericSize = "";

    @Required private String pantsSize = "";
    @Required private String pantsNumericSize = "";

    @Required private String toddlerSize = "";
    @Required private String kidSize = "";

    @Required private String neck = "";
    @Required private String chest = "";

    // BABY
    @Required private String clothing = "";
    @Required private String shoes = "";

    @Required private String notes = "";
    @Required private String attachmentNames = "";

    @Required private Boolean isBaby = false;
    @Required private Boolean isWomen = false;
    @Required private Boolean isGirl = false;
    @Required private Boolean isMen = false;
    @Required private Boolean isBoy = false;

    // BOYS
    @Required private String beltsNumericSize = "";
    @Required private String socks = "";

    @Required private String seat = "";

    @Required private String beltSize = "";

    @Required private String created = "";
    @Required private String modified = "";
    @Required private Boolean isPrivate = false;

    @Required private String createdUser = "";

    public DecryptedClothingSizes(String selectionType, String classType, String personName, String sizeName, String sizeCategory, String topsSize, String topsNumericSize, String bottomsSize, String bottomsNumericSize, String dressesSize, String dressesNumericSize, String outWearSize, String outWearNumericSize, String swimWearSize, String swimWearNumericSize, String swimWearBraBandCupSize, String shoeSize, String shoeWidth, String hats, String gloves, String tights, String bust, String waist, String hips, String armLength, String inseam, String torso, String jacketsSize, String jacketsNumericSize, String pantsSize, String pantsNumericSize, String toddlerSize, String kidSize, String neck, String chest, String clothing, String shoes, String notes, String attachmentNames, Boolean isBaby, Boolean isWomen, Boolean isGirl, Boolean isMen, Boolean isBoy, String beltsNumericSize, String socks, String seat, String beltSize, String created, String modified, Boolean isPrivate, String createdUser) {
        this.selectionType = selectionType;
        this.classType = classType;
        this.personName = personName;
        this.sizeName = sizeName;
        this.sizeCategory = sizeCategory;
        this.topsSize = topsSize;
        this.topsNumericSize = topsNumericSize;
        this.bottomsSize = bottomsSize;
        this.bottomsNumericSize = bottomsNumericSize;
        this.dressesSize = dressesSize;
        this.dressesNumericSize = dressesNumericSize;
        this.outWearSize = outWearSize;
        this.outWearNumericSize = outWearNumericSize;
        this.swimWearSize = swimWearSize;
        this.swimWearNumericSize = swimWearNumericSize;
        this.swimWearBraBandCupSize = swimWearBraBandCupSize;
        this.shoeSize = shoeSize;
        this.shoeWidth = shoeWidth;
        this.hats = hats;
        this.gloves = gloves;
        this.tights = tights;
        this.bust = bust;
        this.waist = waist;
        this.hips = hips;
        this.armLength = armLength;
        this.inseam = inseam;
        this.torso = torso;
        this.jacketsSize = jacketsSize;
        this.jacketsNumericSize = jacketsNumericSize;
        this.pantsSize = pantsSize;
        this.pantsNumericSize = pantsNumericSize;
        this.toddlerSize = toddlerSize;
        this.kidSize = kidSize;
        this.neck = neck;
        this.chest = chest;
        this.clothing = clothing;
        this.shoes = shoes;
        this.notes = notes;
        this.attachmentNames = attachmentNames;
        this.isBaby = isBaby;
        this.isWomen = isWomen;
        this.isGirl = isGirl;
        this.isMen = isMen;
        this.isBoy = isBoy;
        this.beltsNumericSize = beltsNumericSize;
        this.socks = socks;
        this.seat = seat;
        this.beltSize = beltSize;
        this.created = created;
        this.modified = modified;
        this.isPrivate = isPrivate;
        this.createdUser = createdUser;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSizeCategory() {
        return sizeCategory;
    }

    public void setSizeCategory(String sizeCategory) {
        this.sizeCategory = sizeCategory;
    }

    public String getTopsSize() {
        return topsSize;
    }

    public void setTopsSize(String topsSize) {
        this.topsSize = topsSize;
    }

    public String getTopsNumericSize() {
        return topsNumericSize;
    }

    public void setTopsNumericSize(String topsNumericSize) {
        this.topsNumericSize = topsNumericSize;
    }

    public String getBottomsSize() {
        return bottomsSize;
    }

    public void setBottomsSize(String bottomsSize) {
        this.bottomsSize = bottomsSize;
    }

    public String getBottomsNumericSize() {
        return bottomsNumericSize;
    }

    public void setBottomsNumericSize(String bottomsNumericSize) {
        this.bottomsNumericSize = bottomsNumericSize;
    }

    public String getDressesSize() {
        return dressesSize;
    }

    public void setDressesSize(String dressesSize) {
        this.dressesSize = dressesSize;
    }

    public String getDressesNumericSize() {
        return dressesNumericSize;
    }

    public void setDressesNumericSize(String dressesNumericSize) {
        this.dressesNumericSize = dressesNumericSize;
    }

    public String getOutWearSize() {
        return outWearSize;
    }

    public void setOutWearSize(String outWearSize) {
        this.outWearSize = outWearSize;
    }

    public String getOutWearNumericSize() {
        return outWearNumericSize;
    }

    public void setOutWearNumericSize(String outWearNumericSize) {
        this.outWearNumericSize = outWearNumericSize;
    }

    public String getSwimWearSize() {
        return swimWearSize;
    }

    public void setSwimWearSize(String swimWearSize) {
        this.swimWearSize = swimWearSize;
    }

    public String getSwimWearNumericSize() {
        return swimWearNumericSize;
    }

    public void setSwimWearNumericSize(String swimWearNumericSize) {
        this.swimWearNumericSize = swimWearNumericSize;
    }

    public String getSwimWearBraBandCupSize() {
        return swimWearBraBandCupSize;
    }

    public void setSwimWearBraBandCupSize(String swimWearBraBandCupSize) {
        this.swimWearBraBandCupSize = swimWearBraBandCupSize;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    public String getShoeWidth() {
        return shoeWidth;
    }

    public void setShoeWidth(String shoeWidth) {
        this.shoeWidth = shoeWidth;
    }

    public String getHats() {
        return hats;
    }

    public void setHats(String hats) {
        this.hats = hats;
    }

    public String getGloves() {
        return gloves;
    }

    public void setGloves(String gloves) {
        this.gloves = gloves;
    }

    public String getTights() {
        return tights;
    }

    public void setTights(String tights) {
        this.tights = tights;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHips() {
        return hips;
    }

    public void setHips(String hips) {
        this.hips = hips;
    }

    public String getArmLength() {
        return armLength;
    }

    public void setArmLength(String armLength) {
        this.armLength = armLength;
    }

    public String getInseam() {
        return inseam;
    }

    public void setInseam(String inseam) {
        this.inseam = inseam;
    }

    public String getTorso() {
        return torso;
    }

    public void setTorso(String torso) {
        this.torso = torso;
    }

    public String getJacketsSize() {
        return jacketsSize;
    }

    public void setJacketsSize(String jacketsSize) {
        this.jacketsSize = jacketsSize;
    }

    public String getJacketsNumericSize() {
        return jacketsNumericSize;
    }

    public void setJacketsNumericSize(String jacketsNumericSize) {
        this.jacketsNumericSize = jacketsNumericSize;
    }

    public String getPantsSize() {
        return pantsSize;
    }

    public void setPantsSize(String pantsSize) {
        this.pantsSize = pantsSize;
    }

    public String getPantsNumericSize() {
        return pantsNumericSize;
    }

    public void setPantsNumericSize(String pantsNumericSize) {
        this.pantsNumericSize = pantsNumericSize;
    }

    public String getToddlerSize() {
        return toddlerSize;
    }

    public void setToddlerSize(String toddlerSize) {
        this.toddlerSize = toddlerSize;
    }

    public String getKidSize() {
        return kidSize;
    }

    public void setKidSize(String kidSize) {
        this.kidSize = kidSize;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getClothing() {
        return clothing;
    }

    public void setClothing(String clothing) {
        this.clothing = clothing;
    }

    public String getShoes() {
        return shoes;
    }

    public void setShoes(String shoes) {
        this.shoes = shoes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public Boolean getBaby() {
        return isBaby;
    }

    public void setBaby(Boolean baby) {
        isBaby = baby;
    }

    public Boolean getWomen() {
        return isWomen;
    }

    public void setWomen(Boolean women) {
        isWomen = women;
    }

    public Boolean getGirl() {
        return isGirl;
    }

    public void setGirl(Boolean girl) {
        isGirl = girl;
    }

    public Boolean getMen() {
        return isMen;
    }

    public void setMen(Boolean men) {
        isMen = men;
    }

    public Boolean getBoy() {
        return isBoy;
    }

    public void setBoy(Boolean boy) {
        isBoy = boy;
    }

    public String getBeltsNumericSize() {
        return beltsNumericSize;
    }

    public void setBeltsNumericSize(String beltsNumericSize) {
        this.beltsNumericSize = beltsNumericSize;
    }

    public String getSocks() {
        return socks;
    }

    public void setSocks(String socks) {
        this.socks = socks;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getBeltSize() {
        return beltSize;
    }

    public void setBeltSize(String beltSize) {
        this.beltSize = beltSize;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public DecryptedClothingSizes() {
    }
}