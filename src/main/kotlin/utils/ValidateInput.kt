package utils

import java.util.*

object ValidateInput {

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

}