/**
* Created by Admin on 2017/9/15.
*/
new Vue({
    el: '#app',
    data:function() {
        var validatePass = function(rule, value, callback){
        	if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.formCustom.passCheck !== '') {
                    // 对第二个密码框单独验证
                    this.$refs.formCustom.validateField('passCheck');
                }
                callback();
            }
        };
        var validatePassCheck = function(rule, value, callback){
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.formCustom.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        var validateName = function(rule, value, callback) {
            if (!value) {
                callback(new Error('企业账户不能为空'));
            }else{
                callback();
            }
        };
        return {
            userType:'',
            formCustom: {
                companyId:'',
                password: '',
                passCheck: '',
                name: ''
            },
            ruleCustom: {
                password: [
                    { validator: validatePass.bind(this), trigger: 'blur' }
                ],
                passCheck: [
                    { validator: validatePassCheck.bind(this), trigger: 'blur' }
                ],
                name: [
                    { validator: validateName.bind(this), trigger: 'blur' }
                ]
            },
            current: 0,/*添加企业信息步骤*/
            state:true,/*步骤状态*/
            columns: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center',
                    fixed: 'left'
                },
                {
                    title: '企业名称',
                    key: 'name',
                    width: 200,
                    align: 'center'
                },
                {
                    title: '营业执照编号',
                    key: 'businessLicence',
                    width: 200,
                    align: 'center'
                },
                {
                    title: '营业执照有效期',
                    key: 'realLinceTime',
                    width: 200,
                    align: 'center',
                    sortable: true
                },
                {
                    title: '经营许可证',
                    key: 'businessCertificate',
                    width: 200,
                    align: 'center'
                },
                {
                    title: '经营许可证有效期',
                    key: 'realCertTime',
                    width: 200,
                    align: 'center',
                    sortable: true
                },
                {
                    title: '经营范围',
                    key: 'businessScope',
                    width: 200,
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '法人代表',
                    key: 'represent',
                    width: 200,
                    align: 'center'
                },
                {
                    title: '法人代表联系电话',
                    key: 'representPhone',
                    width: 200,
                    align: 'center'
                },
                {
                    title: '企业联系人',
                    key: 'linkman',
                    width: 200,
                    align: 'center'
                },
                {
                    title: '企业联系人电话',
                    key: 'represent',
                    width: 200,
                    align: 'center'
                },
                {
                    title: '省',
                    key: 'province',
                    width: 200,
                    align: 'center'
                },
                {
                    title: '市',
                    key: 'city',
                    width: 200,
                    align: 'center'
                },
                {
                    title: '区',
                    key: 'county',
                    width: 200,
                    align: 'center'
                },
                {
                    title: '操作',
                    key: 'action',
                    align: 'center',
                    width: 200,
                    fixed: 'right',
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
                                            this.openState = "查看";
                                            this.company = JSON.parse(JSON.stringify(this.data1[params.index]));
                                            this.modal1 = true;
                                        }.bind(this)
                                    }
                                }, '查看')
                            ]);
                        }

                    }.bind(this)
                }],
            company: {
                name: '',
                businessLicence: '',
                businLicenceTime: '',
                businessCertificate: '',
                businCertificateTime: '',
                linkman: '',
                linkPhone: '',
                represent: '',
                representPhone: '',
                province: '',
                city: '',
                county: '',
                businessScope: ''
            },
            ruleValidate: {
                name: [
                    {required: true, message: '公司名称不能为空', trigger: 'blur'}
                ],
                businessLicence: [
                    {required: true, message: '营业执照不能为空', trigger: 'blur'}
                ]
            },
            theCity: ['浙江','杭州市','江干区'],
            data1: [],
            allChecked: '',
            theChecked: [],
            goodType: [],
            totalRecord: 0,
            content: {},
            that: this,
            page: {
                current: 1,
                pageNum: 20
            },
            op: 0,
            title: '',
            modal1: false,
            position: 0,
            searchText: '',
            loading: false,
            openState: '',
            cityData: []
        }
    },
    created: function () {
        var _self = this;
        var textState=JSON.parse(Cookies.get("state"));
        if(textState!=null){
            if(textState.ID==0){
                window.location.href="../../state.html";
            }else if(textState.ID==1){
                if(textState.roleID==1){
                    _self.userType=1;
                }else if(textState.roleID==2){
                    _self.userType=2;
                }
                _self.getAll();
            }
        }else {
            window.location.href="../../state.html";
        }
        /// 获得地区
        $.getJSON('../../cityData.json',function (data) {
            _self.cityData = data;
        });
    },
    mounted: function () {
        this.$refs.head.style.display = 'block';
    },
    methods: {
        submit: function (name) {
            var _self=this;
            _self.$refs[name].validate(function (valid) {
                if (valid) {
                    $.ajax({
                        type: 'GET',
                        url: dataUrl.company.add,
                        data:_self.formCustom,
                        cache: false,
                        success: function (data) {
                            _self.$refs['formCustom'].resetFields();
                            _self.$Message.success('提交成功!');
                            _self.state = true;
                            _self.modal1 = false;
                            _self.getAll();
                        }
                    });
                } else {
                    _self.$Message.error('表单验证失败!');
                }
            });
        },
        reset: function (name) {
            this.$refs[name].resetFields();
        },
        search: function() { // 查询
            var _self = this;
            $.ajax({
                type: 'GET',
                url: dataUrl.carrier.search + encodeURI(this.searchText),
                cache: false,
                success: function (data) {
                    if (data != null && data.dataList != undefined) {
                        var d;
                        _self.data1 = data.dataList;
                        _self.totalRecord = data.totalRecord;
                        _self.theChecked = [];
                        for (var i = 0; i < _self.data1.length; i++) {
                            d = new Date(data.dataList[i].businLicenceTime);
                            _self.data1[i].realLinceTime = d.getFullYear()+'-'+(d.getMonth()+ 1)+'-'+ (d.getDate());
                            d = new Date(data.dataList[i].businCertificateTime);
                            _self.data1[i].realCertTime = d.getFullYear()+'-'+(d.getMonth()+ 1)+'-'+ (d.getDate());
                        }
                    } else {
                        _self.data1 = [];
                    }
                }
            });
        },
        upMessage: function () {
            var _self = this;
            _self.loading = true;
            _self.$refs.company.validate( function(valid){
                if(_self.openState==="查看"){
                    _self.loading = false;
                    _self.modal1 = false;
                }else {
                    if (valid) {
                        _self.company.province = _self.theCity[0];
                        _self.company.city = _self.theCity[1];
                        _self.company.county = _self.theCity[2];
                        if (_self.company.businLicenceTime instanceof Date) {
                            _self.company.businLicenceTime = _self.month(_self.company.businLicenceTime);
                        }
                        if (_self.company.businCertificateTime instanceof Date) {
                            _self.company.businCertificateTime = _self.month(_self.company.businCertificateTime);
                        }
                        if (_self.openState == "添加") {
                            _self.postData(_self, dataUrl.carrier.insert, _self.company);
                        }
                        else if (_self.openState == "修改") {
                            _self.postData(_self, dataUrl.carrier.upDate, _self.company);
                        }
                    } else {
                        _self.loading = false;
                    }
                }
            });
        },
        month: function (date) {
            var m ='';
            if (date.getMonth()+1 < 10) {
                m = "0"+(date.getMonth()+1);
            } else {
                m = date.getMonth()+1;
            }
            return date.getFullYear() +'-'+ m + '-' + date.getDate();
        },
        cancel: function () {
            this.modal1 = false;
        },
        open: function(obj) {
            if ($.trim(obj.currentTarget.innerText)== "添加") {
                for (var key in this.company) {
                    this.company[key] = '';
                }
                this.openState = $.trim(obj.currentTarget.innerText);
            }
            //this.$refs['formValidate'].resetFields();
            this.modal1 = true;
        },
        chooseAll: function (selection) { // 全选
            this.theChecked = [];
            for (var i = 0 ; i < selection.length; i++) {
                this.theChecked.push(selection[i].id);
            }
        },
        change: function (index) {
            this.openState = "修改";
            this.company = JSON.parse(JSON.stringify(this.data1[index]));
            this.modal1 = true;
            // this.op = 1;
        },
        time: function (times) {
            // console.log(times);
            var d = new Date(parseInt(times));
            return d.getFullYear()+'/'+(d.getMonth()+1)+'/'+d.getDate();
        },
        deleteOne: function (index) {
            var arr = [this.data1[index].id];
            var _self = this;
            $.ajax({
                type: 'GET',
                url: dataUrl.carrier.del + arr,
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
                    url: dataUrl.carrier.del+_self.theChecked,
                    cache: false,
                    success: function (data) {
                        alert('删除'+_self.theChecked.length+'条数据');
                        _self.getAll();
                    }
                });
            }
        },
        changePage: function (cur) {
            //  跳转页面
            // 分页跳转
            this.page.current = cur;
            this.getAll();
        },
        getAll: function () {
            var _self = this;
            $.ajax({
                type: 'GET',
                url: dataUrl.carrier.all,
                data:_self.page,
                cache: false,
                success: function (data) {
                    if (data != null && data.dataList != undefined) {
                        var d;
                        _self.data1 = data.dataList;
                        _self.totalRecord = data.totalRecord;
                        _self.theChecked = [];
                        for (var i = 0; i < _self.data1.length; i++) {
                            d = new Date(data.dataList[i].businLicenceTime);
                            _self.data1[i].realLinceTime = d.getFullYear()+'-'+(d.getMonth()+ 1)+'-'+ (d.getDate());
                            d = new Date(data.dataList[i].businCertificateTime);
                            _self.data1[i].realCertTime = d.getFullYear()+'-'+(d.getMonth()+ 1)+'-'+ (d.getDate());
                        }
                    } else {
                        _self.data1 = [];
                    }
                }
            });
        },
        callback: function (data) {
            this.page.current = data;
            this.getAll();
        },
        postData: function(_self, url, data) {
            $.ajax({
                type: 'POST',
                url: url,
                data:data,
                cache: false,
                success: function (data) {
                    if(_self.openState == "添加"){
                        _self.state=false;
                        _self.loading = false;
                        _self.current += 1;
                        $.get(dataUrl.company.getID+_self.company.name,function (data) {
                            _self.formCustom.companyId=data.dataList[0].id;
                        });
                    }else{
                        _self.loading = false;
                        _self.$Message.info('上传成功');
                        _self.modal1 = false;
                        _self.getAll();
                    }
                }
            });
        }
    }
});