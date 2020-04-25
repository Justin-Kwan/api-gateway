package pipelinetests

final object MockPipelineJson {

  val mockPipelineJson1: String = """{
    "pipelines": []
  }"""

  val mockPipelineJson2: String = """{
    "pipelines": [
      {
        "name": "pipeline1",
        "middleware requests": [{
            "name": "middleware1",
            "url": "middleware1.com",
            "protocol": "rest",
            "method": "post",
            "body": "request body",
            "successResponse": "user authorized"
          }
        ],
        "service requests": [{
            "name": "service1",
            "url": "service1.com",
            "protocol": "rest",
            "method": "get",
            "body": "request body"
          }
        ]
      }
    ]
  }"""

  val mockPipelineJson3: String = """{
    "pipelines": [
      {
        "name": "pipeline1",
        "middlewares": [],
        "service requests": [{
            "name": "service1",
            "url": "service1.com",
            "protocol": "rest",
            "method": "get"
          }
        ]
      }
    ]
  }"""

  val mockPipelineJson4: String = """{
    "pipelines": [
      {
        "name": "pipeline1",
        "middleware requests": [{
            "name": "middleware1",
            "url": "middleware1.com",
            "protocol": "rest",
            "method": "post",
            "successResponse": "user authorized"
          }
        ],
        "service requests": []
      }
    ]
  }"""

  val mockPipelineJson5: String = """{
    "pipelines": [
      {
        "name": "pipeline1",
        "middleware requests": [{
            "name": "middleware1",
            "url": "middleware1.com",
            "protocol": "rest",
            "method": "post",
            "successResponse": "user authorized"
          },
          {
            "name": "middleware2",
            "url": "middleware2.com",
            "protocol": "rest",
            "method": "post",
            "successResponse": "user authorized"
          }
        ],
        "service requests": [{
            "name": "service1",
            "url": "service1.com",
            "protocol": "rest",
            "method": "patch"
          },
          {
            "name": "service2",
            "url": "service2.com",
            "protocol": "rest",
            "method": "put"
          },
          {
            "name": "service3",
            "url": "service3.com",
            "protocol": "rest",
            "method": "delete"
            }
        ]
      }
    ]
  }"""

}
