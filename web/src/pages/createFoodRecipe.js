import TheCookBookClient from '../api/theCookBookClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class CreateRecipe extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        //this.dataStore.addChangeListener(this.redirectToViewDrinkRecipe);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new TheCookBookClient();
    }

    /**
     * Method to run when the create playlist submit button is pressed. Call the MusicPlaylistService to create the
     * playlist.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const creator = document.getElementById('creator').value;
        const recipeTitle = document.getElementById('recipe-title').value;
        let raw = document.getElementById('ingredients').value;
        let kvs = raw.split(/\n/);
        let map ={};
        console.log(kvs);
        for(let kv of kvs){
            let [k,v] = kv.split(":"); 
            map[k] = v; 
        }
        console.log(map);
        const ingredients = map;
        console.log(ingredients);
        const instructionSteps = document.getElementById('instruction-steps').value;
        const timeEstimate = document.getElementById('time-estimate').value;
        const description = document.getElementById('description').value;
        const descriptionTags = document.getElementById('description-tags').value;
        const foodCategory = document.getElementById('food-category').value;
        const foodItem = document.getElementById('food-item').value;
        const allergies = document.getElementById('allergies').value;
        const ratings = new Map;

        const foodRecipe = await this.client.createFoodRecipe(creator, recipeTitle, ingredients, instructionSteps, timeEstimate, description, descriptionTags, foodCategory, foodItem, allergies, ratings, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('foodRecipes', foodRecipe);
        console.log('foodRecipes', foodRecipe);
        this.redirectToViewFoodRecipe()
    }

    /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
    redirectToViewFoodRecipe() {
        const foodRecipe = this.dataStore.get('foodRecipes');
        if (foodRecipe != null) {
            window.location.href = `/foodRecipe.html?creator=${foodRecipe.creator}&recipeTitle=${foodRecipe.recipeTitle}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createFoodRecipe = new CreateRecipe();
    createFoodRecipe.mount();
};

window.addEventListener('DOMContentLoaded', main);