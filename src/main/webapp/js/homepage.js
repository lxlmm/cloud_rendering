/*
    var Main = {
    }
    var Component = Vue.extend(Main)
    //跟下面效果一样
    new Component().$mount('#container')

    new Vue({
        el: "#container",
        components: {
            'component': Component
        }
    })
*/

// var container = new Vue({
//     el: "#container",
//     data: {
//         flag: "homepage",
//         returnFlag: "homepage",
//         username:"",
//
//         columnsReceive: [
//             {
//                 type: 'selection',
//                 width: 50,
//                 align: 'center',
//             },
//             {
//                 title: 'IsRead',
//                 key: 'read',
//                 width: 90,
//                 // render: (h, params) => {
//                 //     return h('div', [
//                 //         h('Badge', {
//                 //             props: {
//                 //                 type: 'error',
//                 //                 text: 'No'
//                 //             },
//                 //             //on 无用
//                 //             on: {
//                 //                 click: () => {
//                 //                     alert('点中Bagde')
//                 //                 }
//                 //             }
//                 //         }, ),
//                 //     ]);
//                 // }
//
//             },
//             {
//                 title: 'Sender',
//                 key: 'sender'
//             },
//             {
//                 title: 'Theme',
//                 key: 'theme'
//             },
//             {
//                 title: 'Time',
//                 key: 'time'
//             },
//             {
//                 title: 'Todo',
//                 key: 'see',
//                 width: 80,
//                 align: 'center',
//                 render: (h, params) => {
//                     return h('div', [
//                         h('Button', {
//                             props: {
//                                 type: 'primary',
//                                 size: 'small'
//                             },
//                             on: {
//                                 click: () => {
//                                     container.returnFlag = 'receive';
//                                     console.log(params.index);
//                                     console.log(container.dataReceive[params.index].id);
//                                     var id = container.dataReceive[params.index].id;
//                                     SendRequest("/getMsg?id="+id, function (msg) {
//                                         console.log(msg);
//                                         var jMsg = JSON.parse(msg);
//                                         console.log(jMsg);
//                                         container.dataMsg = jMsg;
//                                     })
//                                     container.trans('message');
//                                 }
//                             }
//                         }, 'View'),
//                     ]);
//                 }
//             }
//         ],
//         dataReceive: [],
//         columnsList: [
//             {
//                 type: 'selection',
//                 width: 50,
//                 align: 'center',
//             },
//             {
//                 title: 'Name',
//                 key: 'name',
//             },
//             {
//                 title: 'Username',
//                 key: 'username'
//             }
//         ],
//         dataList: [],
//         columnsDraft: [
//             {
//                 type: 'selection',
//                 width: 50,
//                 align: 'center',
//             },
//             {
//                 title: 'Receiver',
//                 key: 'receiver',
//             },
//             {
//                 title: 'Theme',
//                 key: 'theme'
//             },
//             {
//                 title: 'Time',
//                 key: 'time'
//             },
//             {
//                 title: 'Todo',
//                 key: 'see',
//                 width: 80,
//                 align: 'center',
//                 render: (h, params) => {
//                     return h('div', [
//                         h('Button', {
//                             props: {
//                                 type: 'primary',
//                                 size: 'small'
//                             },
//                             on: {
//                                 click: () => {
//                                     container.returnFlag = 'draft';
//                                     console.log(params.index);
//                                     console.log(container.dataDraft[params.index].id);
//                                     var id = container.dataDraft[params.index].id;
//                                     SendRequest("/getMsg?id="+id, function (msg) {
//                                         console.log(msg);
//                                         var jMsg = JSON.parse(msg);
//                                         console.log(jMsg);
//                                         container.dataMsg = jMsg;
//                                     })
//                                     container.trans('update');
//                                 }
//                             }
//                         }, 'View'),
//                     ]);
//                 }
//             }
//         ],
//         dataDraft: [],
//         columnsSend: [
//             {
//                 type: 'selection',
//                 width: 50,
//                 align: 'center',
//             },
//             {
//                 title: 'Receiver',
//                 key: 'receiver',
//             },
//             {
//                 title: 'Theme',
//                 key: 'theme'
//             },
//             {
//                 title: 'Time',
//                 key: 'time'
//             },
//             {
//                 title: 'Todo',
//                 key: 'see',
//                 width: 80,
//                 align: 'center',
//                 render: (h, params) => {
//                     return h('div', [
//                         h('Button', {
//                             props: {
//                                 type: 'primary',
//                                 size: 'small'
//                             },
//                             on: {
//                                 click: () => {
//                                     container.returnFlag = 'send';
//                                     console.log(params.index);
//                                     console.log(container.dataSend[params.index].id);
//                                     var id = container.dataSend[params.index].id;
//                                     SendRequest("/getMsg?id="+id, function (msg) {
//                                         console.log(msg);
//                                         var jMsg = JSON.parse(msg);
//                                         console.log(jMsg);
//                                         container.dataMsg = jMsg;
//                                     })
//                                     container.trans('message');
//                                 }
//                             }
//                         }, 'View'),
//                     ]);
//                 }
//             }
//         ],
//         dataSend: [],
//         dataMsg: []
//     },
//     methods: {
//         getUsername: function (username) {
//             this.username = username;
//         },
//         trans: function (name) {
//             this.flag = name;
//             console.log(name);
//             var username = document.getElementsByTagName("p")[0].innerHTML;
//             console.log(username);
//             if (name=="receive"||name=="list"||name=="draft"||name=="send") {
//                 SendRequest("/"+name+"Msg?username="+username, function (list) {
//                     console.log(list);
//                     dataReceive=list;
//                     var jList = JSON.parse(list);
//                     console.log(jList);
//                     switch (name) {
//                         case "receive":
//                             container.dataReceive = jList.filter(function (jList) {
//                                 return jList;
//                             });
//                         case "list":
//                             container.dataList = jList.filter(function (jList) {
//                                 return jList;
//                             });
//                         case "draft":
//                             container.dataDraft = jList.filter(function (jList) {
//                                 return jList;
//                             });
//                         case "send":
//                             container.dataSend = jList.filter(function (jList) {
//                                 return jList;
//                             });
//                     }
//
//                 })
//             }
//         },
//         show (index) {
//             this.$Modal.info({
//                 title: 'Msg Info',
//                 content: `Id：${this.dataReceive[index].id}<br>Sender：${this.dataReceive[index].sender}<br>Theme：${this.dataReceive[index].theme}`
//             })
//         },
//         handleSelectAll (status) {
//             this.$refs.selection.selectAll(status);
//         }
//     }
// })

function getTime(t){
    var date=new Date(t);
    var y=date.getFullYear() + '-';
    var m= (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var d= (date.getDate()<10 ? '0'+date.getDate():date.getDate()) + ' ';
    var time=y+m+d;
    var h=(date.getHours()<10 ? '0'+date.getHours():date.getHours()) + ':';
    var mm=(date.getMinutes()<10 ? '0'+date.getMinutes():date.getMinutes()) + ':';
    var s=(date.getSeconds()<10 ? '0'+date.getSeconds():date.getSeconds());
    return y+m+d+h+mm+s;
}

