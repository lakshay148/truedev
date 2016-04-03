package com.truedev.application.core;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by framgia on 11/23/15.
 */
public class ItemTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> tTypeToken) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, tTypeToken);
        final TypeAdapter<JsonElement> elementTypeAdapter = gson.getAdapter(JsonElement.class);
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter jsonWriter, T value) throws IOException {
                delegate.write(jsonWriter, value);
            }

            @Override
            public T read(JsonReader jsonReader) throws IOException {
                JsonElement jsonElement = elementTypeAdapter.read(jsonReader);
                JsonObject jsonObject;
                if (jsonElement.isJsonObject()) {
                     jsonObject = jsonElement.getAsJsonObject();
                }
                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }
}
