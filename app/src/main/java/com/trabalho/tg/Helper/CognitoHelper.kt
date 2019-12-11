package com.trabalho.tg.Helper

import android.content.Context
import android.os.AsyncTask
import com.amazonaws.mobileconnectors.cognitoidentityprovider.*
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler
import com.amazonaws.regions.Regions
import com.amazonaws.services.cognitoidentityprovider.model.InvalidPasswordException
import kotlinx.android.synthetic.main.fragment_login.*
import kotlin.coroutines.coroutineContext

class CognitoHelper(context: Context){
    /*


    var userPool = CognitoUserPool(context,userPoolId,clientID,clientSecret,cognitoRegion)



    fun registerUser(userName: String, email: String, senha: String){
        var signupCallBack : SignUpHandler = object : SignUpHandler {
            override fun onSuccess(cognitoUser: CognitoUser, userConfirmed: Boolean, cognitoUserCodeDeliveryDetails: CognitoUserCodeDeliveryDetails){
                var result : Int
                if (!userConfirmed){
                    println("falta confirmar, mensagem enviada para: " + cognitoUserCodeDeliveryDetails.destination)
                }
                else{
                    println("logado")
                }

            }
            override fun onFailure(e: Exception){
                //println(e.localizedMessage)
                e.printStackTrace()
                when(e){
                    is InvalidPasswordException ->{
                        println("Senha inválida para o formato AWS")
                    }
                }
            }

        }

        println("TESTE")
        var userAttributes = CognitoUserAttributes()

        userAttributes.addAttribute("given_name", userName)
        userAttributes.addAttribute("email",email)
        //userAttributes.addAttribute("password")

       // var cognitoSettings = CognitoUserSettings(context)
        println("Testando")
        //userPool.
        userPool.signUpInBackground(userName,senha ,userAttributes,null,signupCallBack)

        //println(userPool.getUser())
    }

    fun confirmTask(context : Context) :String{
        val result = arrayOf<String>()

        var confirmationCallback  : GenericHandler = object : GenericHandler{
            override fun onSuccess() {
                result.set(0,"Succeeded!")
            }

            override fun onFailure(e : Exception) {
                result.set(0,"Failed: " + e.message)
            }
        }

        var usersSettings = CognitoUserSettings()
        var user : CognitoUser = userPool.getUser()

        user.confirmSignUp(result.get(0), false, confirmationCallback)

        return result.get(0)
    }
*/

}
