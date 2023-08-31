import TheCookBookClient from '../api/theCookBookClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/*
The code below this comment is equivalent to...
const EMPTY_DATASTORE_STATE = {
    'search-criteria': '',
    'search-results': [],
};
...but uses the "KEY" constants instead of "magic strings".
The "KEY" constants will be reused a few times below.
*/




/**
 * Logic needed for the view playlist page of the website.
 */
class getDrinkRecipes extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'viewAll', 'displaySearchResults', 'getHTMLForSearchResults'], this);

        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
        console.log("getDrinkRecipes constructor");
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        // Wire up the form's 'submit' event and the button's 'click' event to the search method
        this.header.addHeaderToPage();

        this.client = new TheCookBookClient();

        this.viewAll();
    }

    /**
     * Uses the client to perform the search, 
     * then updates the datastore with the criteria and results.
     * @param evt The "event" object representing the user-initiated event that triggered this method.
     */
    async viewAll() {
        const results = await this.client.getDrinkRecipes();

        this.dataStore.setState({
            results: results,
        });
    }

    /**
     * Pulls search results from the datastore and displays them on the html page.
     */
    displaySearchResults() {
        const drinkRecipes = this.dataStore.get('results');

        const viewDrinkRecipesDisplay = document.getElementById('drink-recipe-results-display');

        viewDrinkRecipesDisplay.innerHTML = this.getHTMLForSearchResults(drinkRecipes);
    }

    /**
     * Create appropriate HTML for displaying searchResults on the page.
     * @param searchResults An array of playlists objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
    getHTMLForSearchResults(searchResults) {
        if (searchResults.length === 0) {
            return '<h4>No results found</h4>';
        }

        let html = '<table><tr><th>Name</th><th>Song Count</th><th>Tags</th></tr>';
        for (const res of searchResults) {
            html += `
            <tr>
                <td>
                    <a href="playlist.html?id=${res.id}">${res.name}</a>
                </td>
                <td>${res.songCount}</td>
                <td>${res.tags?.join(', ')}</td>
            </tr>`;
        }
        html += '</table>';

        return html;
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const getDrinkRecipes = new getDrinkRecipes();
    getDrinkRecipes.mount();
};

window.addEventListener('DOMContentLoaded', main);