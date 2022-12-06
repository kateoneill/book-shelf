package utils

import java.util.Scanner

object ValidateInput {

    /**
     * reads valid genre and checks it in validator
     *
     * @return if entered genre is valid or otherwise
     */
    fun readValidGenre(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (GenreValidation.isValidGenre(input))
                return input
            else {
                print("Invalid genre: $input.  Please try again: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }

    /**
     * reads valid URL and checks it in validator
     *
     * @return if entered URL is valid or otherwise
     */
    fun readValidURL(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (URLValidation.isValidURL(input))
                return input
            else {
                print("Invalid URL: $input.  Please try again: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }

    /**
     * reads valid email and checks it in validator
     *
     * @return if entered email is valid or otherwise
     */
    fun readValidEmail(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (EmailValidation.isValidEmail(input))
                return input
            else {
                print("Invalid email: $input.  Please try again: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }

    /**
     * reads valid biography and checks it in validator
     *
     * @return if entered biography is valid or otherwise
     */
    fun readValidBio(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (BioValidation.isValidBio(input))
                return input
            else {
                print("Invalid bio: $input.  Please try again: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }

    /**
     * reads valid pace and checks it in validator
     *
     * @return if entered pace is valid or otherwise
     */

    fun readValidPace(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (PaceValidation.isValidPace(input))
                return input
            else {
                print("Invalid pace: $input.  Please try again: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }

    /**
     * reads valid progress and checks it in validator
     *
     * @return if entered progress is valid or otherwise
     */
    fun readValidProgress(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (ProgressValidation.isValidProgress(input))
                return input
            else {
                print("Invalid progress: $input.  Please try again: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }

    /**
     * reads valid rating and checks it in validator
     *
     * @return if entered rating is valid or otherwise
     */
    fun readValidRating(prompt: String?): Int {
        print(prompt)
        var input = Scanner(System.`in`).nextInt()
        do {
            if (RatingValidation.isValidRating(input))
                return input
            else {
                print("Invalid rating: $input.  Please try again: ")
                input = Scanner(System.`in`).nextInt()
            }
        } while (true)
    }
}
