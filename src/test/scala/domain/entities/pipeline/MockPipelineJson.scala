package pipelinetests

final object MockPipelineJson {

  val mockPipelineJson0: String = """{
    "pipelines": []
  }"""

  val mockPipelineJson1: String = """{
    "pipelines": [
      {
        "name": "pipeline empty requests",
        "middleware requests": [],
        "service requests": []
      }
    ]
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
        "middleware requests": [],
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

  val mockPipelineJson4: String = """{
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
        "service requests": []
      }
    ]
  }"""

  val mockPipelineJson5: String = """{
    "pipelines": [
      {
        "name": "pipeline with many services",
        "middleware requests": [{
            "name": "middleware1",
            "url": "middleware1.com",
            "protocol": "rest",
            "method": "post",
            "body": "request body",
            "successResponse": "user authorized"
          },
          {
            "name": "middleware2",
            "url": "middleware2.com",
            "protocol": "rest",
            "method": "delete",
            "body": "request body",
            "successResponse": "user authorized"
          }
        ],
        "service requests": [{
            "name": "service1",
            "url": "service1.com",
            "protocol": "rest",
            "method": "patch",
            "body": "request body"
          },
          {
            "name": "service2",
            "url": "service2.com",
            "protocol": "rest",
            "method": "put",
            "body": "request body"
          },
          {
            "name": "service3",
            "url": "service3.com",
            "protocol": "rest",
            "method": "delete",
            "body": "request body"
            }
        ]
      }
    ]
  }"""

  val mockPipelineJson6: String = """{
    "pipelines": [
      {
        "name": "pipeline1 with many services",
        "middleware requests": [
          {
            "name": "pipeline 1 middleware1",
            "url": "middleware1.com",
            "protocol": "rest",
            "method": "post",
            "body": "pipeline 1 request body",
            "successResponse": "user authorized"
          },
          {
            "name": "pipeline 1 middleware2",
            "url": "middleware2.com",
            "protocol": "rest",
            "method": "delete",
            "body": "pipeline 1 request body",
            "successResponse": "user authorized"
          }
        ],
        "service requests": [{
            "name": "pipeline 1 service1",
            "url": "service1.com",
            "protocol": "rest",
            "method": "patch",
            "body": "pipeline 1 request body"
          },
          {
            "name": "pipeline 1 service2",
            "url": "service2.com",
            "protocol": "rest",
            "method": "put",
            "body": "pipeline 1 request body"
          },
          {
            "name": "pipeline 1 service3",
            "url": "service3.com",
            "protocol": "rest",
            "method": "delete",
            "body": "pipeline 1 request body"
          }
        ]
      },
      {
        "name": "pipeline2 with many services",
        "middleware requests": [{
            "name": "pipeline 2 middleware1",
            "url": "middleware1.com",
            "protocol": "rest",
            "method": "post",
            "body": "pipeline 2 request body",
            "successResponse": "user authorized"
          },
          {
            "name": "pipeline 2 middleware2",
            "url": "middleware2.com",
            "protocol": "rest",
            "method": "delete",
            "body": "pipeline 2 request body",
            "successResponse": "user authorized"
          },
          {
            "name": "pipeline 2 middleware3",
            "url": "middleware3.com",
            "protocol": "rest",
            "method": "patch",
            "body": "pipeline 2 request body",
            "successResponse": "user authorized"
          },
          {
            "name": "pipeline 2 middleware4",
            "url": "middleware4.com",
            "protocol": "rest",
            "method": "put",
            "body": "pipeline 2 request body",
            "successResponse": "rate limit not exceeded"
          }
        ],
        "service requests": [{
            "name": "pipeline 2 service1",
            "url": "service1.com",
            "protocol": "rest",
            "method": "patch",
            "body": "pipeline 2 request body"
          },
          {
            "name": "pipeline 2 service2",
            "url": "service2.com",
            "protocol": "rest",
            "method": "put",
            "body": "pipeline 2 request body"
          },
          {
            "name": "pipeline 2 service3",
            "url": "service3.com",
            "protocol": "rest",
            "method": "delete",
            "body": "pipeline 2 request body"
          }
        ]
      },
      {
        "name": "pipeline3 with many services",
        "middleware requests": [{
            "name": "pipeline 3 middleware1",
            "url": "middleware1.com",
            "protocol": "rest",
            "method": "post",
            "body": "pipeline 3 request body",
            "successResponse": "user authorized"
          },
          {
            "name": "pipeline 3 middleware2",
            "url": "middleware2.com",
            "protocol": "rest",
            "method": "delete",
            "body": "pipeline 3 request body",
            "successResponse": "user authorized"
          },
          {
            "name": "pipeline 3 middleware3",
            "url": "middleware3.com",
            "protocol": "rest",
            "method": "patch",
            "body": "pipeline 3 request body",
            "successResponse": "user authorized"
          },
          {
            "name": "pipeline 3 middleware4",
            "url": "middleware4.com",
            "protocol": "rest",
            "method": "put",
            "body": "pipeline 3 request body",
            "successResponse": "rate limit not exceeded"
          }
        ],
        "service requests": [{
            "name": "pipeline 3 service1",
            "url": "service1.com",
            "protocol": "rest",
            "method": "patch",
            "body": "pipeline 3 request body"
          },
          {
            "name": "pipeline 3 service2",
            "url": "service2.com",
            "protocol": "rest",
            "method": "put",
            "body": "pipeline 3 request body"
          },
          {
            "name": "pipeline 3 service3",
            "url": "service3.com",
            "protocol": "rest",
            "method": "delete",
            "body": "pipeline 3 request body"
          },
          {
            "name": "pipeline 3 service4",
            "url": "service4.com",
            "protocol": "rest",
            "method": "delete",
            "body": "pipeline 3 request body"
          },
          {
            "name": "pipeline 3 service5",
            "url": "service5.com",
            "protocol": "rest",
            "method": "delete",
            "body": "pipeline 3 request body"
          }
        ]
      }
    ]
  }"""

}
