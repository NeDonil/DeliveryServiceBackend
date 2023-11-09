GET api/product?product_id={id}- full product
    
    { FullProductDTO
        "id": 1,
        "title": "Cucumber",
        "photo": null,
        "description": "desk2",
        "count": 200,
        "price": 150000
    }
GET api/product/group - group list
    
    [GroupDTO
        {
            "id": 1,
            "name": "General"
        },
    ]
GET api/product/group/{group_id} - group items(maybe pagination)
    [
        { FullProductDTO
            "id": 1,
            "title": "Cucumber",
            "photo": null,
            "description": "desk2",
            "count": 200,
            "price": 150000
        },
    ]
Customer:
GET api/customer/ - get customer details(addresses)
    { CustomerDTO
        "id": 1,
        "fio": "Dmitry Rudnev",
        "email": "dimas@gmail.com",
        "addresses": 
        [   AddressDTO 
            {
                "id": 1,
                "value": "Kukolkina 11 p4 kv 10"
            },
            {
                "id": 2,
                "value": "Kukolkina 11 p5 kv 11"
            }
        ]
    }
GET api/customer/order - orders history
    [
        { OrderDTO
            "id": 3,
            "comment": null,
            "address": AddressDTO
            {

            },
            "items": OrderItemDTO
            [ 
                {
                    "id": 4,
                    "count": 100,
                    "product": 
                    { ProductDTO
                        "id": 3,
                        "title": "Apple",
                        "count": 23,
                        "price": 30000
                    }
                }
            ]
        }
    ]
GET, PUT api/customer/order/current - get current order
    Response
    {
        "id": 1,
        "comment": null,
        "address": "",
        "shortItems": [
            {
                "id": 1,
                "count": 10,
                "product": {
                    "id": 2,
                    "title": "Alpen Gold",
                    "count": 1,
                    "price": 100000
                }
            }
        ]
    }
    Request
    {
        "comment": null,
        "address": "",
        "items": [ ShortOrderItemDTO
            {
                "id": 1,
                "count": 10,
                "product": { ShortProduct.Request
                    "id": 2,
                }
            }
        ]
    }
GET api/customer/order/{orderId} - get full order
    {
        "id": 1,
        "comment": null,
        "address": 
        { AddressDTO

        },
        "items": [
            {
                "id": 1,
                "count": 10,
                "product": { ProductDTO
                    "id": 2,
                    "title": "Alpen Gold",
                    "count": 1,
                    "price": 100000
                }
            }
        ]
    }
GET api/customer/order/{orderId}/action/{actionId} - make/reject order
    {}
Assembler: 
GET api/assembler/orders - get assembly orders
    [
        {
            "id": 2,
            "comment": null,
            "address": 
            { AddressDTO

            },
            "items": [
                { EmployeeOrder
                    "id": 2,
                    "count": 17,
                    "product": { ShortProductDTO
                        "id": 1,
                        "title": "Cucumber",
                    }
                },
            ]
        }
    ]
GET api/assembler/order/{orderID}/action/{action} - assemble/refuse order
{}
Courier:
GET api/courier/order - get delivery orders
    [
        {
            "id": 1,
            "comment": null,
            "address": "",
            "items": [ OrderItemDTO
                {
                    "id": 1,
                    "count": 10,
                    "product": { ShortProductDTO
                        "id": 2,
                        "title": "Alpen Gold",
                    }
                }
            ]
        }
    ]
GET api/courier/order/{orderID}/action/{action} - delivery/refuse order
{}
Admin:
GET api/admin/assembler - get assemblers list
    [
        {
            "id": 1,
            "email": "Marina Kovaleva"
        }
    ]
GET, POST, PUT, DELETE api/admin/assembler?id - assembler CRUD
    Response:
    [
        {
            "id": 1,
            "fio": "Marina Kovaleva"
        }
    ]
    Request:
    {
        "fio": "",
        "email": "",
        "password": ""
    }
GET api/admin/courier - get couriers list
GET, POST, PUT, DELETE api/admin/courier?id - courier CRUD
    Response:
    [
        {
            "id": 1,
            "fio": "Marina Kovaleva"
        }
    ]
    Request:
    {
        "fio": "",
        "email": "",
        "password": ""
    }
GET api/admin/orders/{status} - get orders by status
    [
        { OrderDTO
            "id": 1,
            "comment": null,
            "address": 
            { AddressDTO
                
            },
            "items": [ OrderItemDTO
                {
                    "id": 1,
                    "count": 10,
                    "product": { ProductDTO
                        "id": 2,
                        "title": "Alpen Gold",
                        "count": 1,
                        "price": 100000
                    }
                }
            ]
        }
    ]