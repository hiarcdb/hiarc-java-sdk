package com.hiarcdb.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class NullableAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        List<String> nonNullableFieldNames = new ArrayList<>(Arrays.asList(
            "key",
            "name",
            "description",
            "metadata"
        ));
        TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(NullableAdapterFactory.this, type);
        TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                JsonObject jsonObject = delegateAdapter.toJsonTree(value).getAsJsonObject();
                for (String name : nonNullableFieldNames) {
                    if (jsonObject.has(name) && jsonObject.get(name) instanceof JsonNull) {
                        jsonObject.remove(name);
                    }
                }
                if (jsonObject.has("metadata")) {
                    JsonElement src = jsonObject.get("metadata");
                    Gson gson = new GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create();
                    jsonObject.add("metadata", JsonParser.parseString(gson.toJson(src)).getAsJsonObject());
                }

                out.setSerializeNulls(true);
                elementAdapter.write(out, jsonObject);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                return delegateAdapter.read(in);
            }

        };
    }
}