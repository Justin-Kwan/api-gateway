// package networking
//
// import requestclient.HttpRequestClient
//
// final object RequestSelector {
//
//   def selectRequestMethod(httpVerb: String): (u: String, r: String) = {
//     val requestMethod = requestProtocol match {
//       case "get"    => HttpRequestClient.makeGetRequest(url: String, requestBody: String)
//       case "post"   => HttpRequestClient.makePostRequest(url: String, requestBody: String)
//       case "put"    => HttpRequestClient.makePutRequest(url: String, requestBody: String)
//       case "patch"  => HttpRequestClient.makePatchRequest(url: String, requestBody: String)
//       case "delete" => HttpRequestClient.makeDeleteRequest(url: String, resourceParam: String)
//       case _        =>
//     }
//
//     return requestMethod
//   }
//
// }
