package ru.alexeyp.data.network.model;

import com.google.gson.annotations.SerializedName;

public class DTORepository {

    public String name;

    @SerializedName("full_name")
    public String fullName;

    public String description;

    public DTOUser owner;
}
