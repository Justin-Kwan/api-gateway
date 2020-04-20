import java.io._

import org.scalatest.FunSpec

import sharedutilities.JsonReader

final class JsonReaderTest extends FunSpec {

  final val EmptyMockJson = ""
  final val ShortMockJson = "{ \"mockJsonData\": \"mock data\"}"
  final val LongMockJson = """[
{
  "_id": "5e96bbf1ec89f70ef940d2bd",
  "index": 0,
  "guid": "23663a5e-4fa5-467b-b81c-486653d44e48",
  "isActive": true,
  "balance": "$2,894.68",
  "picture": "http://placehold.it/32x32",
  "age": 33,
  "eyeColor": "blue",
  "name": "Mcclure Snow",
  "gender": "male",
  "company": "NAVIR",
  "email": "mccluresnow@navir.com",
  "phone": "+1 (961) 552-3572",
  "address": "886 Borinquen Pl, Bennett, Missouri, 7512",
  "about": "Culpa ad ullamco ea commodo labore adipisicing est aute consectetur proident reprehenderit non qui. Pariatur in laborum mollit irure aliquip quis excepteur quis nulla. Laboris adipisicing adipisicing eiusmod ullamco velit irure ex labore culpa aliquip voluptate ad adipisicing. Occaecat incididunt magna veniam elit mollit in enim in voluptate mollit id. Aliqua cupidatat deserunt cillum anim.\r\n",
  "registered": "2015-12-18T03:14:36 +05:00",
  "latitude": -21.214939,
  "longitude": 93.095523,
  "tags": [
    "velit",
    "excepteur",
    "veniam",
    "officia",
    "veniam",
    "ad",
    "cillum"
  ],
  "friends": [
    {
      "id": 0,
      "name": "Kelli Buchanan"
    },
    {
      "id": 1,
      "name": "Alyson Shepard"
    },
    {
      "id": 2,
      "name": "Mckay Russo"
    }
  ],
  "greeting": "Hello, Mcclure Snow! You have 10 unread messages.",
  "favoriteFruit": "strawberry"
},
{
  "_id": "5e96bbf156ac6237c77a8b50",
  "index": 1,
  "guid": "b0c38138-a6b2-4422-9541-93d4c612b8b5",
  "isActive": false,
  "balance": "$2,935.76",
  "picture": "http://placehold.it/32x32",
  "age": 33,
  "eyeColor": "green",
  "name": "Maude Robles",
  "gender": "female",
  "company": "XOGGLE",
  "email": "mauderobles@xoggle.com",
  "phone": "+1 (993) 471-3861",
  "address": "932 Williams Avenue, Goochland, Guam, 2003",
  "about": "Pariatur consequat ea velit nulla dolor qui anim ad pariatur commodo ut amet reprehenderit cillum. Lorem occaecat velit ex consequat ex laboris cupidatat nulla minim. Et commodo nulla ex aliquip cillum. Dolore ullamco fugiat incididunt laborum voluptate et labore nostrud eiusmod laborum aute. Esse aliquip dolore magna qui nostrud cillum magna voluptate amet amet commodo. Enim dolor dolore proident eiusmod commodo sit ullamco cillum duis exercitation eiusmod eiusmod est commodo.\r\n",
  "registered": "2016-10-24T11:50:57 +04:00",
  "latitude": -65.300902,
  "longitude": -160.338621,
  "tags": [
    "fugiat",
    "proident",
    "enim",
    "sint",
    "aute",
    "quis",
    "tempor"
  ],
  "friends": [
    {
      "id": 0,
      "name": "Velma Young"
    },
    {
      "id": 1,
      "name": "Nadia Duffy"
    },
    {
      "id": 2,
      "name": "Queen Freeman"
    }
  ],
  "greeting": "Hello, Maude Robles! You have 8 unread messages.",
  "favoriteFruit": "banana"
},
{
  "_id": "5e96bbf178b9841c166c33ca",
  "index": 2,
  "guid": "382f8093-3dea-4493-8425-e163e7440b94",
  "isActive": false,
  "balance": "$3,958.16",
  "picture": "http://placehold.it/32x32",
  "age": 34,
  "eyeColor": "brown",
  "name": "Joy Tyson",
  "gender": "female",
  "company": "SKINSERVE",
  "email": "joytyson@skinserve.com",
  "phone": "+1 (978) 418-2893",
  "address": "146 Newton Street, Blende, New Hampshire, 1427",
  "about": "Adipisicing ad eu eu labore laboris adipisicing pariatur ea et velit. Aute pariatur aute magna labore sunt veniam aute et ad tempor commodo aliqua. Qui dolor mollit ex anim commodo consequat. Eu ut mollit laboris Lorem aliquip adipisicing dolor nulla reprehenderit ad dolore Lorem ipsum. Non reprehenderit ex proident eiusmod aute pariatur dolor non aliqua. Ullamco ut enim nisi officia ipsum elit proident duis. Ex non nulla commodo in aliquip aliquip dolor nulla laborum minim.\r\n",
  "registered": "2014-03-02T01:40:33 +05:00",
  "latitude": 35.13812,
  "longitude": 139.403515,
  "tags": [
    "exercitation",
    "quis",
    "cupidatat",
    "dolore",
    "in",
    "ullamco",
    "cupidatat"
  ],
  "friends": [
    {
      "id": 0,
      "name": "Vargas Gill"
    },
    {
      "id": 1,
      "name": "Rene Berger"
    },
    {
      "id": 2,
      "name": "Atkins Joseph"
    }
  ],
  "greeting": "Hello, Joy Tyson! You have 6 unread messages.",
  "favoriteFruit": "apple"
},
{
  "_id": "5e96bbf12c2f4457a194a513",
  "index": 3,
  "guid": "e584475d-c6ab-4740-ba75-dc528c610aa8",
  "isActive": false,
  "balance": "$1,355.75",
  "picture": "http://placehold.it/32x32",
  "age": 34,
  "eyeColor": "brown",
  "name": "Ophelia Gillespie",
  "gender": "female",
  "company": "LOCAZONE",
  "email": "opheliagillespie@locazone.com",
  "phone": "+1 (842) 490-2162",
  "address": "232 Vanderveer Place, Blairstown, Iowa, 4756",
  "about": "Officia et dolore do excepteur est occaecat tempor commodo sit tempor do. Sunt eiusmod quis consectetur pariatur eiusmod. Qui mollit pariatur nisi sunt nisi anim consectetur culpa deserunt adipisicing sit. Voluptate mollit do minim aute fugiat amet voluptate et. Dolore nulla est dolor eu incididunt non laborum incididunt aliquip. Eu adipisicing veniam aute ea eiusmod laborum consectetur esse. Exercitation proident cillum ea excepteur reprehenderit et ad aliqua id eu minim duis incididunt.\r\n",
  "registered": "2016-09-18T01:59:31 +04:00",
  "latitude": -55.471885,
  "longitude": -69.201892,
  "tags": [
    "ea",
    "enim",
    "id",
    "labore",
    "aliquip",
    "minim",
    "aliqua"
  ],
  "friends": [
    {
      "id": 0,
      "name": "Blankenship Rollins"
    },
    {
      "id": 1,
      "name": "Alvarado Wong"
    },
    {
      "id": 2,
      "name": "Melinda Mcbride"
    }
  ],
  "greeting": "Hello, Ophelia Gillespie! You have 5 unread messages.",
  "favoriteFruit": "banana"
},
{
  "_id": "5e96bbf132571addd82073f2",
  "index": 4,
  "guid": "a2bf6b1e-4457-4caf-8fcb-58ba801d9f01",
  "isActive": false,
  "balance": "$2,963.61",
  "picture": "http://placehold.it/32x32",
  "age": 35,
  "eyeColor": "brown",
  "name": "Landry Holder",
  "gender": "male",
  "company": "MUSAPHICS",
  "email": "landryholder@musaphics.com",
  "phone": "+1 (890) 431-2501",
  "address": "981 Kenmore Terrace, Dola, Minnesota, 3524",
  "about": "Nulla elit minim pariatur ea est cupidatat pariatur do duis consequat consequat fugiat. Nostrud commodo mollit sit dolor officia. Est tempor nulla cupidatat do nostrud eu. Dolor non eiusmod proident occaecat nulla qui.\r\n",
  "registered": "2015-07-27T02:36:40 +04:00",
  "latitude": 1.143875,
  "longitude": 114.776249,
  "tags": [
    "incididunt",
    "culpa",
    "ut",
    "adipisicing",
    "commodo",
    "elit",
    "officia"
  ],
  "friends": [
    {
      "id": 0,
      "name": "Phyllis Newman"
    },
    {
      "id": 1,
      "name": "Peck Hurley"
    },
    {
      "id": 2,
      "name": "Terry Norman"
    }
  ],
  "greeting": "Hello, Landry Holder! You have 3 unread messages.",
  "favoriteFruit": "banana"
},
{
  "_id": "5e96bbf1a80dd9845d7316ad",
  "index": 5,
  "guid": "a6d48481-7120-4514-ac92-de6c7fafcf30",
  "isActive": false,
  "balance": "$2,087.59",
  "picture": "http://placehold.it/32x32",
  "age": 23,
  "eyeColor": "green",
  "name": "Gabrielle Ashley",
  "gender": "female",
  "company": "INQUALA",
  "email": "gabrielleashley@inquala.com",
  "phone": "+1 (824) 426-3059",
  "address": "694 Fleet Walk, Dante, Hawaii, 636",
  "about": "Adipisicing cillum dolore in velit culpa cupidatat sunt velit proident duis sit quis culpa. Aliqua veniam sit elit Lorem et proident officia laborum velit qui tempor ad aliquip. Aliquip fugiat esse cillum voluptate.\r\n",
  "registered": "2014-11-08T07:43:15 +05:00",
  "latitude": -36.711383,
  "longitude": 62.209901,
  "tags": [
    "laboris",
    "magna",
    "proident",
    "incididunt",
    "duis",
    "ullamco",
    "commodo"
  ],
  "friends": [
    {
      "id": 0,
      "name": "Graham Rivers"
    },
    {
      "id": 1,
      "name": "Stephanie Meadows"
    },
    {
      "id": 2,
      "name": "Tami Ayala"
    }
  ],
  "greeting": "Hello, Gabrielle Ashley! You have 4 unread messages.",
  "favoriteFruit": "banana"
}
]"""

  final val filePaths = Map(
    "GATEWAY_CONFIG" -> "configs/gateway-config.json"
  )

  def writeTestTextToFile(fileText: String, fileKey: String): Unit = {
    val file = new File(
      getClass
      .getClassLoader
      .getResource(filePaths(fileKey))
      .getPath
    )
    val pw = new PrintWriter(file)
    pw.write(fileText)
    pw.close
  }

  describe("openFile() tests") {
    it("should read mock json data from gateway config file") {
      writeTestTextToFile(ShortMockJson, "GATEWAY_CONFIG")
      var gatewayConfigJson: String = JsonReader.openFile("GATEWAY_CONFIG")
      assert(gatewayConfigJson == ShortMockJson)
    }

    it("should read empty mock json data from gateway config file") {
      writeTestTextToFile(EmptyMockJson, "GATEWAY_CONFIG")
      var gatewayConfigJson: String = JsonReader.openFile("GATEWAY_CONFIG")
      assert(gatewayConfigJson == EmptyMockJson)
    }

    it("should read long mock json data from gateway config file") {
      writeTestTextToFile(LongMockJson, "GATEWAY_CONFIG")
      var gatewayConfigJson: String = JsonReader.openFile("GATEWAY_CONFIG")
      assert(gatewayConfigJson == LongMockJson)
    }
  }

}
