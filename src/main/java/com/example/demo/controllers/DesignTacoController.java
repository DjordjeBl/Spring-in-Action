package com.example.demo.controllers;

import com.example.demo.models.Ingredient;
import com.example.demo.models.Ingredient.Type;
import com.example.demo.models.Taco;
import com.example.demo.models.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
  * Handle HTTP GET requests where the request path is /design
  * Build a list of ingredients
  * Hand off the requests and the ingredient data to a view template to be rendered as HTML and sent to the requesting web browser
*/

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase());
            filterByType(ingredients, type);
        }
    }

    /*
        When a request is processed by a Spring MVC controller method, it often needs to prepare data that will be displayed in a view.
        This data is usually stored in a model, which is a container for data passed between the controller and the view.
     */
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    /*
        1. We start by converting the 'List' of 'ingredients' into a stream using the '.stream()' method.
           A stream provides a way to perform operations on collections of data in a more concise way compared to traditional loop-based approaches.
        2. We then use '.filter()' method on the stream. The '.filter()' method takes a lambda expression ('x -> x.getType().equals(type)') as an argument.
           This lambda expression is used to test each element in the stream ('x') to see if its 'Type' matches the 'type' parameter provided to the method.
        3. Elements that pass the filter condition (i.e., where 'x.getType().equals(type)' is 'true') are retained in the stream.
        4. Finally, it collects the filtered elements into a new 'List' using the '.collect(Collectors.toList())' method.
           This creates a new list containing only the 'Ingredient' objects whose 'Type' matches the provided 'type'.

        In summary, this method takes a list of 'Ingredient' objects and returns a new list containing only those with a specific 'Type'.
        It uses Java's stream and lambda expressions to perform the filtering operation.
     */
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
