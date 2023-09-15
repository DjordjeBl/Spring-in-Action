package com.example.demo;

import com.example.demo.models.Ingredient;
import com.example.demo.models.Ingredient.Type;
import com.example.demo.repository.IngredientRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/*
  This class is designed to facilitate the conversion of ingredient IDs to 'Ingredient' objects.
  It's often used in Spring apps where data needs to be converted between different formats or types.
*/

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
  private IngredientRepository ingredientRepo;

  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }


  @Override
  public Ingredient convert(String id) {
    return ingredientRepo.findById(id).orElse(null);
  }
}