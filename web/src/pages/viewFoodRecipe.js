import TheCookBookClient from '../api/theCookBookClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class ViewFoodRecipe extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addFoodRecipeToPage', 'addRating', 'deleteFoodRecipe'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addFoodRecipeToPage);
        console.log("add food recipe")
        this.header = new Header(this.dataStore);
        console.log("viewfoodrecipe constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const foodRecipeCreator = urlParams.get('creator');
        const foodRecipeTitle = urlParams.get('recipeTitle');
        document.getElementById('food-recipe-title').innerText = "Loading Food Recipe ...";
        const foodRecipe = await this.client.getFoodRecipe(foodRecipeCreator, foodRecipeTitle);
        this.dataStore.set('foodRecipes',foodRecipe);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('rate-food-recipe').addEventListener('click', this.addRating);
        document.getElementById('delete-food-recipe').addEventListener('click', this.deleteDrinkRecipe);

        this.header.addHeaderToPage();

        this.client = new TheCookBookClient();
        this.clientLoaded();
    }

    /**
     * When the playlist is updated in the datastore, update the playlist metadata on the page.
     */
    addFoodRecipeToPage() {
        const foodRecipe = this.dataStore.get('foodRecipes');
        console.log(foodRecipe);
        if (foodRecipe == null) {
            return;
        }
        document.getElementById('food-recipe-title').innerText = foodRecipe.recipeTitle;
        document.getElementById('food-recipe-creator').innerText = foodRecipe.creator;
        document.getElementById('food-recipe-ingredients').innerText = JSON.stringify(foodRecipe.ingredients);
        document.getElementById('food-recipe-time-estimate').innerText = foodRecipe.timeEstimate;
        document.getElementById('food-recipe-instructions').innerText = foodRecipe.instructionSteps;
        document.getElementById('food-recipe-description').innerText = foodRecipe.description;
        document.getElementById('food-recipe-description-tags').innerText = foodRecipe.descriptionTags;
        document.getElementById('food-recipe-category').innerText = foodRecipe.drinkCategory;
        document.getElementById('food-recipe-item').innerText = foodRecipe.drinkItem;
        document.getElementById('food-recipe-allergies').innerText = foodRecipe.allergies;
        document.getElementById('food-recipe-ratings').innerText = JSON.stringify(foodRecipe.ratings);
    }

    /**
     * Method to run when the add song playlist submit button is pressed. Call the MusicPlaylistService to add a song to the
     * playlist.
     */
    async addRating() {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        let foodRecipe = this.dataStore.get('foodRecipes');
        if (foodRecipe == null) {
            return;
        }

        document.getElementById('rate-food-recipe').innerText = 'Adding...';
        const rating = document.getElementById('rating').value;
        const user = document.getElementById('user').value;
        const creator = foodRecipe.creator;
        const recipeTitle = foodRecipe.recipeTitle;


        drinkRecipe = await this.client.rateFoodRecipe(creator, recipeTitle, rating, user, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');           
        });

        this.dataStore.set('foodRecipes', foodRecipe);

        document.getElementById('rate-food-recipe').innerText = 'Add rating';
        document.getElementById("add-rating-form").reset();

        window.location.href = `/foodRecipe.html?creator=${creator}&recipeTitle=${recipeTitle}`;
    }

    async deleteFoodRecipe(){
        const urlParams = new URLSearchParams(window.location.search);
        const foodRecipeCreator = urlParams.get('creator');
        const foodRecipeTitle = urlParams.get('recipeTitle');
        await this.client.deleteFoodRecipe(drinkRecipeCreator, drinkRecipeTitle);
        console.log("delete");
        window.location.href = `/getFoodRecipes.html`;
    }
    
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewFoodRecipe = new ViewFoodRecipe();
    viewFoodRecipe.mount();
};

window.addEventListener('DOMContentLoaded', main);