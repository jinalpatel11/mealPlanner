package com.example.mealplanner.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.util.List;

// User.java
public class User  implements Parcelable {
    private String username;
    private String password;
    
    private String profilePhoto;
    private String firstName;
    private  String lastName;
    private String birthday;
    private String height;

    private String heightUnit;
    private  String weight ;

    private String weightUnit;

  private  String  targetWeight;
  private  String targetWeightUnit;
    private String activityLevel;

    private  String email;

    private  String sex;


    private List<String> selectedGoals;


    public User(String username, String password, String profilePhoto, String firstName,
                String lastName, String birthday, String height, String weight,
                String activityLevel, String email) {
        this.username = username;
        this.password = password;
        this.profilePhoto = profilePhoto;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.email = email;
    }

    public User() {

    }

    protected User(Parcel in) {
        username = in.readString();
        password = in.readString();
        profilePhoto = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        birthday = in.readString();
        height = in.readString();
        heightUnit = in.readString();
        weight = in.readString();
        weightUnit = in.readString();
        targetWeight = in.readString();
        targetWeightUnit = in.readString();
        activityLevel = in.readString();
        email = in.readString();
        selectedGoals = in.createStringArrayList();
        sex = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(profilePhoto);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(birthday);
        dest.writeString(height);
        dest.writeString(heightUnit);
        dest.writeString(weight);
        dest.writeString(weightUnit);
        dest.writeString(targetWeight);
        dest.writeString(targetWeightUnit);
        dest.writeString(activityLevel);
        dest.writeString(email);
        dest.writeStringList(selectedGoals);
        dest.writeString(sex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getBirthdate() {
        return birthday;
    }

    public void setBirthdate(String birthdate) {
        this.birthday = birthdate;
    }

    // Other methods...

    // Example method to convert birthdate string to Date object
    public Date getBirthdateAsDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            return sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getTargetWeightUnit() {
        return targetWeightUnit;
    }

    public void setTargetWeightUnit(String targetWeightUnit) {
        this.targetWeightUnit = targetWeightUnit;
    }

    public String getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(String targetWeight) {
        this.targetWeight = targetWeight;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

