/**
 * Created by Admin on 2017/9/15.
 */
new Vue({
    el: '#app',
    data:function() {
        return {
            userType: '',
            columns: [
                {
                    type: 'selection',
                    align: 'center'
                },
                {
                    title: '编码',
                    key: 'code',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '名称',
                    key: 'name',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '货物类型',
                    key: 'reserveTwo',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '货物安全卡',
                    key: 'reserveThree',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '应急方案',
                    key: 'reserveOne',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '操作',
                    key: 'action',
                    align: 'center',
                    render: function (h, params) {
                        if(this.userType===1){
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: function () {
                                            this.change(params.index);
                                        }.bind(this)
                                    }
                                }, '修改'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: function () {
                                            this.deleteOne(params.index);
                                        }.bind(this)
                                    }
                                }, '删除')
                            ]);
                        }else{
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: function () {
                                            this.showOne(params.index);
                                        }.bind(this)
                                    }
                                }, '查看')
                            ]);
                        }

                    }.bind(this)
                }],
            data1: [],
            modal1: false,
            allChecked: '',
            theChecked: [],
            goodType: [],
            totalRecord: 0,
            content: {},
            page: {
                current: 1,
                pageNum: 20
            },
            op: 0,
            title: '',
            shade: false,
            position: 0,
            loading: false,
            searchText: '',
            sureName: '确定',
            used: false,
            goods: {
                name: '',
                goodstyleId: '',
                safecardId: '',
                code: '',
                reserveOne: '',
                reserveTwo: '',
                reserveThree: '',
                remark: ''
            },
            ruleValidate: {
                name: [
                    {required: true, message: '货物名称不能为空', trigger: 'blur'}
                ],
                code: [
                    {required: true, message: '货物编号不能为空', trigger: 'blur'}
                ],
                goodstyleId: [
                    {required: true, message: '请选择货物类型', trigger: 'blur', type: 'number'}
                ],
                safecardId: [
                    {required: true, message: '请选择安全卡', trigger: 'blur', type: 'number'}
                ],
                reserveOne: [
                    {required: true, message: '请填写应急方案', trigger: 'blur'}
                ]
            },
            TypeList: [],
            cardList: []
        }
    },
    created: function () {
        var _self = this;
        var textState = JSON.parse(Cookies.get("state"));
        if (textState != null) {
            if (textState.ID == 0) {
                window.location.href = "../../state.html";
            } else if (textState.ID == 1) {
                if (textState.roleID == 1) {
                    _self.userType = 1;
                } else if (textState.roleID == 2) {
                    _self.userType = 2;
                } else {
                    _self.userType = 3;
                }
                _self.getAll();
            }
        } else {
            window.location.href = "../../state.html";
        }
        $.ajax({
            type: 'GET',
            url: dataUrl.goods.goodsType,
            cache: false,
            success: function (data) {
                _self.TypeList = data;
            }
        });
        // 获得所有安全卡类型
        $.ajax({
            type: 'GET',
            url: dataUrl.goods.safeCard,
            cache: false,
            success: function (data) {
                _self.cardList = data;
            }
        });
    },
    mounted: function () {
        this.$refs.head.style.display = 'block';
    },
    methods: {
        search: function () {
            if (this.searchText.replace(/\s/g, '').length < 1) {
                alert('搜索内容不可为空');
            } else {
                var _self = this;
                $.ajax({
                    type: 'GET',
                    url: dataUrl.goods.search + encodeURI(this.searchText.replace(/\s/g, '')),
                    cache: false,
                    success: function (data) {
                        if (typeof data == "object") {
                            _self.totalRecord = data.totalRecord;
                            _self.page.current = data.currentPage;
                            _self.data1 = data.dataList;
                        } else {
                            _self.data1 = [];
                        }
                    }
                });
            }
        },
        showOne: function (index) {
            this.$Modal.info({
                title: '货物信息',
                content: '编号：' + this.data1[index].code + '<br>' +
                '名称：' + this.data1[index].name + '<br>' +
                '货物类型：' + this.data1[index].reserveTwo + '<br>' +
                '货物安全卡：' + this.data1[index].reserveThree + '<br>' +
                '应急方案：' + this.data1[index].reserveOne + '<br>' +
                '备注：' + this.data1[index].remark
            });
        },
        chooseAll: function (selection) { // 全选
            this.theChecked = [];
            for (var i = 0; i < selection.length; i++) {
                this.theChecked.push(selection[i].id);
            }
        },
        change: function (index) {
            this.goods = JSON.parse(JSON.stringify(this.data1[index]));
            this.modal1 = true;
            this.op = 1;
        },
        time: function (times) {
            // console.log(times);
            var d = new Date(parseInt(times));
            return d.getFullYear() + '/' + (d.getMonth() + 1) + '/' + d.getDate();
        },
        cancel: function () {
            this.modal1 = false;
        },
        open: function (name, op) {
            this.op = op;
            if (op == 0) {
                for (var key in this.goods) {
                    this.goods[key] = '';
                }
                this.modal1 = true;
                //addressInit('cmbProvince', 'cmbCity', 'cmbArea');
            }
        },
        deleteOne: function (index) {
            var arr = [this.data1[index].id];
            var _self = this;
            $.ajax({
                type: 'GET',
                url: dataUrl.goods.del + arr,
                cache: false,
                success: function (data) {
                    alert('删除一条数据');
                    _self.getAll();
                }
            });
        },
        moreDelete: function () {
            var _self = this;
            if (this.theChecked.length > 0) {
                $.ajax({
                    type: 'GET',
                    url: dataUrl.goods.del + _self.theChecked,
                    cache: false,
                    success: function (data) {
                        alert('删除' + _self.theChecked.length + '条数据');
                        _self.getAll();
                    }
                });
            }
        },
        changePage: function (cur) { //  跳转页面
            this.page.current = cur;
            this.getAll();
        },
        getAll: function () {
            var _self = this;
            $.ajax({
                type: 'GET',
                url: dataUrl.goods.all,
                data:_self.page,
                cache: false,
                success: function (data) {
                    if (data != null && data.dataList != undefined) {
                        var d;
                        _self.data1 = data.dataList;
                        _self.totalRecord = data.totalRecord;
                        _self.page.current = data.currentPage;
                        _self.theChecked = [];
                    } else {
                        _self.data1 = [];
                    }
                }
            });
        },
        upMessage: function () {
            this.loading = true;
            var _self = this;
            this.$refs.goods.validate(function (valid) {
                if (valid) {
                    var url;
                    if (_self.op == 0) {
                        url = dataUrl.goods.insert;
                    } else if (_self.op == 1) {
                        url = dataUrl.goods.upDate;
                    }
                    // 发出请求
                    $.ajax({
                        type: 'POST',
                        url: url,
                        dataType: 'json',
                        contentType: "application/json",
                        data:JSON.stringify(_self.goods),
                        cache: false,
                        success: function (data) {
                            _self.loading = false;
                            _self.$Message.info('上传成功');
                            _self.modal1 = false;
                            _self.getAll();
                        },
                        error:function () {
                        	_self.$Message.info('上传失败');
                            _self.loading = false;
                        }
                    });
                } else {
                    _self.loading = false;
                }
            });
        },
        getTheGoods: function (data) {
            this.goods.reserveTwo = data.label;
        },
        getTheSafe: function (data) {
            this.goods.reserveThree = data.label;
        }
    }
});
