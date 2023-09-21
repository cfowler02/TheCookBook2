import TheCookBookClient from '../api/theCookBookClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class ViewDrinkRecipe extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addDrinkRecipeToPage', 'addRating', 'deleteDrinkRecipe'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addDrinkRecipeToPage);
        console.log("add drink recipe")
        this.header = new Header(this.dataStore);
        console.log("viewdrinkrecipe constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const drinkRecipeCreator = urlParams.get('creator');
        const drinkRecipeTitle = urlParams.get('recipeTitle');
        document.getElementById('drink-recipe-title').innerText = "Loading Drink Recipe ...";
        const drinkRecipe = await this.client.getDrinkRecipe(drinkRecipeCreator, drinkRecipeTitle);
        this.dataStore.set('drinkRecipes', drinkRecipe);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('rate-drink-recipe').addEventListener('click', this.addRating);
        document.getElementById('delete-drink-recipe').addEventListener('click', this.deleteDrinkRecipe);

        this.header.addHeaderToPage();

        this.client = new TheCookBookClient();
        this.clientLoaded();
    }

    /**
     * When the playlist is updated in the datastore, update the playlist metadata on the page.
     */
    addDrinkRecipeToPage() {
        const drinkRecipe = this.dataStore.get('drinkRecipes');
        console.log(drinkRecipe);
        if (drinkRecipe == null) {
            return;
        }
        document.getElementById('drink-recipe-title').innerText = drinkRecipe.recipeTitle;
        document.getElementById('drink-recipe-creator').innerText = drinkRecipe.creator;
        document.getElementById('drink-recipe-ingredients').innerText = drinkRecipe.ingredients;
        document.getElementById('drink-recipe-instructions').innerText = drinkRecipe.instructionSteps;
        document.getElementById('drink-recipe-description').innerText = drinkRecipe.description;
        document.getElementById('drink-recipe-description-tags').innerText = drinkRecipe.descriptionTags;
        document.getElementById('drink-recipe-category').innerText = drinkRecipe.drinkCategory;
        document.getElementById('drink-recipe-item').innerText = drinkRecipe.drinkItem;
        document.getElementById('drink-recipe-allergies').innerText = drinkRecipe.allergies;
        document.getElementById('drink-recipe-ratings').innerText = JSON.stringify(drinkRecipe.ratings);
    }

    /**
     * Method to run when the add song playlist submit button is pressed. Call the MusicPlaylistService to add a song to the
     * playlist.
     */
    async addRating() {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        let drinkRecipe = this.dataStore.get('drinkRecipes');
        if (drinkRecipe == null) {
            return;
        }

        document.getElementById('rate-drink-recipe').innerText = 'Adding...';
        const rating = document.getElementById('rating').value;
        const user = document.getElementById('user').value;
        const creator = drinkRecipe.creator;
        const recipeTitle = drinkRecipe.recipeTitle;


        drinkRecipe = await this.client.rateDrinkRecipe(creator, recipeTitle, rating, user, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');           
        });

        this.dataStore.set('drinkRecipes', drinkRecipe);

        document.getElementById('rate-drink-recipe').innerText = 'Add rating';
        document.getElementById("add-rating-form").reset();

        window.location.href = `/drinkRecipe.html?creator=${creator}&recipeTitle=${recipeTitle}`;
    }

    async deleteDrinkRecipe(){
        const urlParams = new URLSearchParams(window.location.search);
        const drinkRecipeCreator = urlParams.get('creator');
        const drinkRecipeTitle = urlParams.get('recipeTitle');
        await this.client.deleteDrinkRecipe(drinkRecipeCreator, drinkRecipeTitle);
        console.log("delete");
        window.location.href = `/index.html`;
    }
    
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewDrinkRecipe = new ViewDrinkRecipe();
    viewDrinkRecipe.mount();
};

window.addEventListener('DOMContentLoaded', main);