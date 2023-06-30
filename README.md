# Design Document

## Instructions

_Replace italicized text (including this text!) with details of the design you are proposing for your team project. (Your replacement text shouldn't be in italics)._

_You should take a look at the [example design document](example-design-document.md) in the same folder as this template for more guidance on the types of information to capture, and the level of detail to aim for._

## TheCookBook Design

## 1. Problem Statement

I as an user want an app that can be used to search and find recipes for both food and drinks. I want the option to search with multiple option or view all and be presented with the multiple recipes and multiple pieces of information about them such as the creator, the ingredients, estimated time to complete, some sort of rating(s), food item name, recipe title, food category, and a list of description tags. I should then be able to open or view a specific recipe and get the same information but also the uploader, a description about what is being made, and a list of instruction steps to make whatever you are making.

## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that you are still debating internally that you might like help working through._

1. Best way to search inputting a keyword without specifing which table attribute it is referencing
2. How much can I switch from string to enum without causing issues or manually inserted hundreds or thousands of enums
3. How can I store and display and image relating to a specific recipe from the table

## 3. Use Cases

U1. As a TheCookBook customer, I want to be able to upload/create a food recipe 

U2. As a TheCookBook customer, I want to be able to upload/create a drink recipe

U3. As a TheCookBook customer, I want to be able to “open up” and view a food recipe in further detail

U4. As a TheCookBook customer, I want to be able to “open up” and view a drink recipe in further detail

U5. As a TheCookBook customer, I want to be able to rate a food recipe good, alright, or bad and display all ratings

U6. As a TheCookBook customer, I want to be able to rate a drink recipe good, alright, or bad and display all ratings

U7. As a TheCookBook customer, I want to be able to view all food recipes sorted by ratings 

U8. As a TheCookBook customer, I want to be able to view all drink recipes sorted by ratings

U9. As a TheCookBook customer, I want to search food recipes by a keyword relating to one of the recipe’s attributes such as ingredients, creator, description tags etc.

U10. As a TheCookBook customer, I want to search drink recipes by a keyword relating to one of the recipe’s attributes such as ingredients, creator, description tags etc.

## 4. Project Scope

### 4.1. In Scope

Upload/Create a food or drink recipe
View a food or drink recipe (specific recipe)
Rate a food or drink recipe
View all food or drink recipes
Search for a food or drink recipe

### 4.2. Out of Scope

Recipe images
Food and Drink Item objects
Enums for allergies, ingredients, description tags, and food and drink categories
Editing already posted recipes
Creator verification (verifies uploader isn't taking credit for or miscrediting someone for a recipe)

# 5. Proposed Architecture Overview

_Describe broadly how you are proposing to solve for the requirements you described in Section 2. This may include class diagram(s) showing what components you are planning to build. You should argue why this architecture (organization of components) is reasonable. That is, why it represents a good data flow and a good separation of concerns. Where applicable, argue why this architecture satisfies the stated requirements._

Class Diagram

# 6. API

## 6.1. Public Models

`FoodRecipe`
```
String Creator
String recipeTitle
List<String> Ingredients
LinkedList<String> instructionSteps
String Description
List<String> DescriptionTags
Int estimatedTimeToCompletion
String FoodCategory
String FoodItem
List<String> Allergies
Map<Integer, Integer> Ratings
```

`DrinkRecipe`
```
String Creator
String recipeTitle
List<String> Ingredients
LinkedList<String> instructionSteps
String Description
List<String> DescriptionTags
String FoodCategory
String FoodItem
List<String> Allergies
Map<Integer, Integer> Ratings
```

## 6.2. Get Food Recipes (All)

_Describe the behavior of the first endpoint you will build into your service API. This should include what data it requires, what data it returns, and how it will handle any known failure cases. You should also include a sequence diagram showing how a user interaction goes from user to website to service to database, and back. This first endpoint can serve as a template for subsequent endpoints. (If there is a significant difference on a subsequent endpoint, review that with your team before building it!)_

_(You should have a separate section for each of the endpoints you are expecting to build...)_

Sequence Diagram

    Input: 
    Output: a list of all food recipes

## 6.3 Get Food Recipe (Viewing one)

_(repeat, but you can use shorthand here, indicating what is different, likely primarily the data in/out and error conditions. If the sequence diagram is nearly identical, you can say in a few words how it is the same/different from the first endpoint)_

    Input: String Creator, String recipeTitle
    Output: a food recipe

## 6.4 Get Food Recipes (Search)

    Input: String keyword
    Output: a list of food recipes related to the search

## 6.5 Get Drink Recipes (All)

    Input: 
    Output: a list of all drink recipes

## 6.6 Get Drink Recipe (Viewing one)

    Input: String Creator, String recipeTitle
    Output: a drink recipe

## 6.7 Get Drink Recipes (Search)

    Input: String keyword
    Output: a list of drink recipes related to the search

## 6.8 Post Food Recipe (Upload/Create Recipe)

    Input: String Creator, String recipeTitle, List<String> Ingredients, List<String> instructionSteps, String Description, List<String> DescriptionTags, Int estimatedTimeToCompletion, String FoodCategory, String FoodItem, List<String> Allergies
    Output: a new food recipe

## 6.9 Put Food Recipe (Rating)

    Input: String Creator, String recipeTitle, Int -1/0/1
    Output: a updated food recipe

## 6.10 Post Drink Recipe (Upload/Create Recipe)

    Input: String Creator, String recipeTitle, List<String> Ingredients, List<String> instructionSteps, String Description, List<String> DescriptionTags, String DrinkCategory, String DrinkItem, List<String> Allergies
    Output: a new drink recipe

## 6.11 Put Drink Recipe (Rating)

    Input: String Creator, String recipeTitle, Int -1/0/1
    Output: a updated food recipe

# 7. Tables

_Define the DynamoDB tables you will need for the data your service will use. It may be helpful to first think of what objects your service will need, then translate that to a table structure, like with the *`Playlist` POJO* versus the `playlists` table in the Unit 3 project._

`FoodRecipe`
```
String Creator // partition key
String recipeTitle // sort key
List<String> Ingredients
List<String> instructionSteps
String Description
List<String> DescriptionTags
Int estimatedTimeToCompletion
String FoodCategory
String FoodItem
List<String> Allergies
Map<Integer, Integer> Ratings
```

`DrinkRecipe`
```
String Creator // partition key
String recipeTitle // sort key
List<String> Ingredients
List<String> instructionSteps
String Description
List<String> DescriptionTags
String FoodCategory
String FoodItem
List<String> Allergies
Map<Integer, Integer> Ratings
```

# 8. Pages

_Include mock-ups of the web pages you expect to build. These can be as sophisticated as mockups/wireframes using drawing software, or as simple as hand-drawn pictures that represent the key customer-facing components of the pages. It should be clear what the interactions will be on the page, especially where customers enter and submit data. You may want to accompany the mockups with some description of behaviors of the page (e.g. “When customer submits the submit-dog-photo button, the customer is sent to the doggie detail page”)_

Wireframes
