package testutilities

import com.google.gson.Gson

import requeststages.MiddlewareRequest
import requeststages.ServiceRequest
import pipeline.Pipeline

/**
 * Assertion class that checks equality for custom predefined objects
 */
final object ObjectMatcher {

  private val gson: Gson = new Gson()

  def areObjectsEqual(object1: Any, object2: Any): Boolean = {
    if(gson.toJson(object1) == gson.toJson(object1)) {
      return true
    }
    else {
      println("Objects did not match")
      println("Object 1: " + gson.toJson(object1))
      println("")
      println("Object 2: " + gson.toJson(object2))
      return false
    }
  }


}
