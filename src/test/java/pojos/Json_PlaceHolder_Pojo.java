package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // it will ignore differences ==
// if you have some different key values in response, how can you make java to ignore them? I use @JsonIgnoreProperties and ignoreUnknown = true
// how do you handle different key-values in response body? I use @JsonIgnoreProperties and ignoreUnknown = true at the top of the pojo class
public class Json_PlaceHolder_Pojo {

    /* pojo means Plain Old Java Object???
    to create pojo follow this steps:
    1) create private variables for every key
    2) create 2 constructor:  with all parameters and without any parameter
    3) create getters and setters
    4) create toString method
     */

    // 1. create private variables for every key
    private Integer userId;
    private String title;
    private Boolean completed;

    // 2) create 2 constructor:  with all parameters and without any parameter


    public Json_PlaceHolder_Pojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public Json_PlaceHolder_Pojo() { // constructor without parameter
    }

    // 3) create getters and setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // 4) create toString method

    @Override
    public String toString() {
        return "Json_PlaceHolder_Pojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
