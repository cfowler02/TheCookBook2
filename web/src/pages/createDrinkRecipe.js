import TheCookBookClient from '../api/theCookBookClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class CreatePlaylist extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewDrinkRecipe'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewDrinkRecipe);
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
        const ingredients = document.getElementById('ingredients').value;
        const instructionSteps = document.getElementById('instruction-steps').value;
        const description = document.getElementById('description').value;
        const descriptionTags = document.getElementById('description-tags').value;
        const drinkCategory = document.getElementById('drink-category').value;
        const drinkItem = document.getElementById('drink-item').value;
        const allergies = document.getElementById('allergies').value;
        const ratings = null;

        const drinkRecipe = await this.client.createDrinkRecipe(creator, recipeTitle, ingredients, instructionSteps, description, descriptionTags, drinkCategory, drinkItem, allergies, ratings, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('drinkRecipes', drinkRecipe);
    }

    /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
    redirectToViewDrinkRecipe() {
        const drinkRecipe = this.dataStore.get('drinkRecipes');
        if (drinkRecipe != null) {
            window.location.href = `/drinkRecipes.html/get?id=${drinkRecipe.creator}/title.html?id=${drinkRecipe.recipeTitle}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createDrinkRecipe = new createDrinkRecipe();
    createDrinkRecipe.mount();
};

window.addEventListener('DOMContentLoaded', main);