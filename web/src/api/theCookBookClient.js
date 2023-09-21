import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class TheCookBookClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = [
            'clientLoaded', 'getIdentity', 'login', 'logout', 'getTokenOrThrow', 'getDrinkRecipe', 'getDrinkRecipes', 'createDrinkRecipe', 'rateDrinkRecipe', 'deleteDrinkRecipe',
            'getFoodRecipe', 'getFoodRecipes', 'createFoodRecipe', 'rateFoodRecipe', 'deleteFoodRecipe', 'handleError'
        ];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Gets the playlist for the given ID.
     * @param id Unique identifier for a playlist
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The playlist's metadata.
     */
    async getDrinkRecipe(creator, recipeTitle, errorCallback) {
        try {
            const response = await this.axiosClient.get(`drinkRecipes/get/${creator}/${recipeTitle}`);
            return response.data.drinkRecipe;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getFoodRecipe(creator, recipeTitle, errorCallback) {
        try {
            const response = await this.axiosClient.get(`foodRecipes/get/${creator}/${recipeTitle}`);
            return response.data.foodRecipe;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets the playlist for the given ID.
     * @param id Unique identifier for a playlist
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The playlist's metadata.
     */
    async deleteDrinkRecipe(creator, recipeTitle, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete a drink recipes.");
            const response = await this.axiosClient.delete(`drinkRecipes/delete/${creator}/${recipeTitle}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.drinkRecipes;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async deleteFoodRecipe(creator, recipeTitle, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete a food recipes.");
            const response = await this.axiosClient.delete(`foodRecipes/delete/${creator}/${recipeTitle}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.foodRecipes;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Create a new playlist owned by the current user.
     * @param name The name of the playlist to create.
     * @param tags Metadata tags to associate with a playlist.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The playlist that has been created.
     */
    async createDrinkRecipe(creator, recipeTitle, ingredients, instructionSteps, description, descriptionTags, drinkCategory, drinkItem, allergies, ratings, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create drink recipes.");
            const response = await this.axiosClient.post(`drinkRecipes/create`, {
                creator: creator,
                recipeTitle: recipeTitle,
                ingredients: ingredients,
                instructionSteps: instructionSteps ? instructionSteps.split(','):null,
                description: description,
                descriptionTags: descriptionTags ? descriptionTags.split(','):null,
                drinkCategory: drinkCategory,
                drinkItem: drinkItem,
                allergies: allergies ? allergies.split(','):null,
                ratings: ratings
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.drinkRecipe;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async createFoodRecipe(creator, recipeTitle, ingredients, instructionSteps, timeEstimate, description, descriptionTags, foodCategory, foodItem, allergies, ratings, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create food recipes.");
            const response = await this.axiosClient.post(`foodRecipes/create`, {
                creator: creator,
                recipeTitle: recipeTitle,
                ingredients: ingredients,
                instructionSteps: instructionSteps ? instructionSteps.split(','):null,
                timeEstimate: timeEstimate,
                description: description,
                descriptionTags: descriptionTags ? descriptionTags.split(','):null,
                foodCategory: foodCategory,
                foodItem: foodItem,
                allergies: allergies ? allergies.split(','):null,
                ratings: ratings
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.foodRecipe;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Add a song to a playlist.
     * @param id The id of the playlist to add a song to.
     * @param asin The asin that uniquely identifies the album.
     * @param trackNumber The track number of the song on the album.
     * @returns The list of songs on a playlist.
     */
    async rateDrinkRecipe(creator, recipeTitle, rating, user, errorCallback) {
        try {
            console.log(user, rating);
            const token = await this.getTokenOrThrow("Only authenticated users can rate a drink recipe.");
            const response = await this.axiosClient.put(`drinkRecipes/rate/`, {
                creator: creator,
                recipeTitle: recipeTitle,
                rating: rating,
                user: user
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.drinkRecipe;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async rateFoodRecipe(creator, recipeTitle, rating, user, errorCallback) {
        try {
            console.log(user, rating);
            const token = await this.getTokenOrThrow("Only authenticated users can rate a food recipe.");
            const response = await this.axiosClient.put(`foodRecipes/rate/`, {
                creator: creator,
                recipeTitle: recipeTitle,
                rating: rating,
                user: user
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.foodRecipe;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Search for a soong.
     * @param criteria A string containing search criteria to pass to the API.
     * @returns The playlists that match the search criteria.
     */
    async getDrinkRecipes(errorCallback) {
        try {
            const response = await this.axiosClient.get(`drinkRecipes`);

            return response.data.drinkRecipeModels;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getFoodRecipes(errorCallback) {
        try {
            const response = await this.axiosClient.get(`foodRecipes`);

            return response.data.foodRecipeModels;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Search for a soong.
     * @param criteria A string containing search criteria to pass to the API.
     * @returns The playlists that match the search criteria.
     
        async search(criteria, errorCallback) {
            try {
                const queryParams = new URLSearchParams({ q: criteria })
                const queryString = queryParams.toString();
    
                const response = await this.axiosClient.get(`/drinkRecipes/search?${filter}/criteria/{criteria}`);
    
                return response.data.playlists;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
    
        }
    */

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}