package com.trabalho.tg.Helper

import android.content.Context
import com.amazonaws.mobileconnectors.cognitoidentityprovider.*
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler
import com.amazonaws.regions.Regions
import com.amazonaws.services.cognitoidentityprovider.model.InvalidPasswordException
import kotlinx.android.synthetic.main.fragment_login.*

class CognitoHelper(context: Context){
    val userPoolId = "us-east-2_lCVIkfltG"
    val clientID = "69iban3fv6ccqm7n9mqsvoa9ba"
    val clientSecret = "1fnqd6hhnu8iuphi1r0j4amqsvgffls0flrufbc1l97o5gffh89k"
    val cognitoRegion = Regions.US_EAST_2

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


}