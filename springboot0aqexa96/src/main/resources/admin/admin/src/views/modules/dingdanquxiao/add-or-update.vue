<template>
	<div class="addEdit-block">
		<template v-if="type=='info'">
			<div class="info-view">
				<div class="info-row"><span class="info-label">订单编号：</span><span class="info-value">{{ruleForm.dingdanbianhao}}</span></div>
				<div class="info-row"><span class="info-label">汽车车牌：</span><span class="info-value">{{ruleForm.qichechepai}}</span></div>
				<div class="info-row"><span class="info-label">汽车类型：</span><span class="info-value">{{ruleForm.qicheleixing}}</span></div>
				<div class="info-row"><span class="info-label">品牌：</span><span class="info-value">{{ruleForm.pinpai}}</span></div>
				<div class="info-row"><span class="info-label">型号：</span><span class="info-value">{{ruleForm.xinghao}}</span></div>
				<div class="info-row"><span class="info-label">租车时间：</span><span class="info-value">{{ruleForm.zucheshijian}}</span></div>
				<div class="info-row"><span class="info-label">取消时间：</span><span class="info-value">{{ruleForm.quxiaoshijian}}</span></div>
				<div class="info-row"><span class="info-label">取消原因：</span><span class="info-value">{{ruleForm.quxiaoyuanyin}}</span></div>
				<div class="info-row"><span class="info-label">用户账号：</span><span class="info-value">{{ruleForm.yonghuzhanghao}}</span></div>
				<el-button @click="back()" style="border: none; cursor: pointer; padding: 0 20px; color: #fff; border-radius: 4px; background: #909399; font-size: 14px; height: 40px; margin-top: 24px;">返回</el-button>
			</div>
		</template>
		<template v-else>
			<el-form
				class="add-update-preview"
				ref="ruleForm"
				:model="ruleForm"
				:rules="rules"
				label-width="180px"
			>
				<template >
					<el-form-item class="input" v-if="type!='info'"  label="订单编号" prop="dingdanbianhao" >
						<el-input v-model="ruleForm.dingdanbianhao" placeholder="订单编号" clearable  :readonly="ro.dingdanbianhao"></el-input>
					</el-form-item>
					<el-form-item v-else class="input" label="订单编号" prop="dingdanbianhao" >
						<el-input v-model="ruleForm.dingdanbianhao" placeholder="订单编号" readonly></el-input>
					</el-form-item>
					<el-form-item class="input" v-if="type!='info'"  label="汽车车牌" prop="qichechepai" >
						<el-input v-model="ruleForm.qichechepai" placeholder="汽车车牌" clearable  :readonly="ro.qichechepai"></el-input>
					</el-form-item>
					<el-form-item v-else class="input" label="汽车车牌" prop="qichechepai" >
						<el-input v-model="ruleForm.qichechepai" placeholder="汽车车牌" readonly></el-input>
					</el-form-item>
					<el-form-item class="input" v-if="type!='info'"  label="汽车类型" prop="qicheleixing" >
						<el-input v-model="ruleForm.qicheleixing" placeholder="汽车类型" clearable  :readonly="ro.qicheleixing"></el-input>
					</el-form-item>
					<el-form-item v-else class="input" label="汽车类型" prop="qicheleixing" >
						<el-input v-model="ruleForm.qicheleixing" placeholder="汽车类型" readonly></el-input>
					</el-form-item>
					<el-form-item class="input" v-if="type!='info'"  label="品牌" prop="pinpai" >
						<el-input v-model="ruleForm.pinpai" placeholder="品牌" clearable  :readonly="ro.pinpai"></el-input>
					</el-form-item>
					<el-form-item v-else class="input" label="品牌" prop="pinpai" >
						<el-input v-model="ruleForm.pinpai" placeholder="品牌" readonly></el-input>
					</el-form-item>
					<el-form-item class="input" v-if="type!='info'"  label="型号" prop="xinghao" >
						<el-input v-model="ruleForm.xinghao" placeholder="型号" clearable  :readonly="ro.xinghao"></el-input>
					</el-form-item>
					<el-form-item v-else class="input" label="型号" prop="xinghao" >
						<el-input v-model="ruleForm.xinghao" placeholder="型号" readonly></el-input>
					</el-form-item>
					<el-form-item class="input" v-if="type!='info'"  label="年份" prop="nianfen" >
						<el-input v-model="ruleForm.nianfen" placeholder="年份" clearable  :readonly="ro.nianfen"></el-input>
					</el-form-item>
					<el-form-item v-else class="input" label="年份" prop="nianfen" >
						<el-input v-model="ruleForm.nianfen" placeholder="年份" readonly></el-input>
					</el-form-item>
					<el-form-item class="input" v-if="type!='info'"  label="日租金" prop="rizujin" >
						<el-input-number v-model="ruleForm.rizujin" placeholder="日租金" :disabled="ro.rizujin"></el-input-number>
					</el-form-item>
					<el-form-item v-else class="input" label="日租金" prop="rizujin" >
						<el-input v-model="ruleForm.rizujin" placeholder="日租金" readonly></el-input>
					</el-form-item>
					<el-form-item class="date" v-if="type!='info'" label="租车时间" prop="zucheshijian" >
						<el-date-picker
							value-format="yyyy-MM-dd HH:mm:ss"
							v-model="ruleForm.zucheshijian" 
							type="datetime"
							:readonly="ro.zucheshijian"
							:picker-options="zucheshijianPickerOptions"
							placeholder="租车时间"
						></el-date-picker>
					</el-form-item>
					<el-form-item class="input" v-else-if="ruleForm.zucheshijian" label="租车时间" prop="zucheshijian" >
						<el-input v-model="ruleForm.zucheshijian" placeholder="租车时间" readonly></el-input>
					</el-form-item>
					<el-form-item class="date" v-if="type!='info'" label="取消时间" prop="quxiaoshijian" >
						<el-date-picker
							value-format="yyyy-MM-dd HH:mm:ss"
							v-model="ruleForm.quxiaoshijian" 
							type="datetime"
							:readonly="ro.quxiaoshijian"
							placeholder="取消时间"
						></el-date-picker>
					</el-form-item>
					<el-form-item class="input" v-else-if="ruleForm.quxiaoshijian" label="取消时间" prop="quxiaoshijian" >
						<el-input v-model="ruleForm.quxiaoshijian" placeholder="取消时间" readonly></el-input>
					</el-form-item>
					<el-form-item class="input" v-if="type!='info'"  label="取消原因" prop="quxiaoyuanyin" >
						<el-input v-model="ruleForm.quxiaoyuanyin" placeholder="取消原因" clearable  :readonly="ro.quxiaoyuanyin"></el-input>
					</el-form-item>
					<el-form-item v-else class="input" label="取消原因" prop="quxiaoyuanyin" >
						<el-input v-model="ruleForm.quxiaoyuanyin" placeholder="取消原因" readonly></el-input>
					</el-form-item>
					<el-form-item class="input" v-if="type!='info'"  label="车商账号" prop="cheshangzhanghao" >
						<el-input v-model="ruleForm.cheshangzhanghao" placeholder="车商账号" clearable  :readonly="ro.cheshangzhanghao"></el-input>
					</el-form-item>
					<el-form-item v-else class="input" label="车商账号" prop="cheshangzhanghao" >
						<el-input v-model="ruleForm.cheshangzhanghao" placeholder="车商账号" readonly></el-input>
					</el-form-item>
					<el-form-item class="input" v-if="type!='info'"  label="用户账号" prop="yonghuzhanghao" >
						<el-input v-model="ruleForm.yonghuzhanghao" placeholder="用户账号" clearable  :readonly="ro.yonghuzhanghao"></el-input>
					</el-form-item>
					<el-form-item v-else class="input" label="用户账号" prop="yonghuzhanghao" >
						<el-input v-model="ruleForm.yonghuzhanghao" placeholder="用户账号" readonly></el-input>
					</el-form-item>
					<el-form-item class="input" v-if="type!='info'"  label="用户姓名" prop="yonghuxingming" >
						<el-input v-model="ruleForm.yonghuxingming" placeholder="用户姓名" clearable  :readonly="ro.yonghuxingming"></el-input>
					</el-form-item>
					<el-form-item v-else class="input" label="用户姓名" prop="yonghuxingming" >
						<el-input v-model="ruleForm.yonghuxingming" placeholder="用户姓名" readonly></el-input>
					</el-form-item>
				</template>
				<el-form-item class="btn">
					<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
						<span class="icon iconfont icon-xihuan"></span>
						确定
					</el-button>
					<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
						<span class="icon iconfont icon-xihuan"></span>
						取消
					</el-button>
					<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
						<span class="icon iconfont icon-xihuan"></span>
						返回
					</el-button>
				</el-form-item>
			</el-form>
		</template>
	</div>
</template>
<script>
	import { 
		isNumber,
	} from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					dingdanbianhao : false,
					qichechepai : false,
					qicheleixing : false,
					pinpai : false,
					xinghao : false,
					nianfen : false,
					rizujin : false,
					qichetupian : false,
					zucheshijian : false,
					quxiaoshijian : false,
					quxiaoyuanyin : false,
					cheshangzhanghao : false,
					yonghuzhanghao : false,
					yonghuxingming : false,
					crossuserid : false,
					crossrefid : false,
				},
			
				ruleForm: {
					dingdanbianhao: '',
					qichechepai: '',
					qicheleixing: '',
					pinpai: '',
					xinghao: '',
					nianfen: '',
					rizujin: '',
					qichetupian: '',
					zucheshijian: '',
					quxiaoshijian: '',
					quxiaoyuanyin: '',
					cheshangzhanghao: '',
					yonghuzhanghao: '',
					yonghuxingming: '',
					crossuserid: '',
					crossrefid: '',
				},

				zucheshijianPickerOptions: {
					disabledDate(time) {
						return time.getTime() < Date.now() - 8.64e7;
					}
				},
				rules: {
					dingdanbianhao: [
					],
					qichechepai: [
					],
					qicheleixing: [
					],
					pinpai: [
					],
					xinghao: [
					],
					nianfen: [
					],
					rizujin: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					qichetupian: [
					],
					zucheshijian: [
					],
					quxiaoshijian: [
					],
					quxiaoyuanyin: [
						{ required: true, message: '取消原因不能为空', trigger: 'blur' },
					],
					cheshangzhanghao: [
					],
					yonghuzhanghao: [
					],
					yonghuxingming: [
					],
					crossuserid: [
					],
					crossrefid: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
			this.ruleForm.zucheshijian = this.getCurDateTime()
			this.ruleForm.quxiaoshijian = this.getCurDateTime()
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='dingdanbianhao'){
							this.ruleForm.dingdanbianhao = obj[o];
							this.ro.dingdanbianhao = true;
							continue;
						}
						if(o=='qichechepai'){
							this.ruleForm.qichechepai = obj[o];
							this.ro.qichechepai = true;
							continue;
						}
						if(o=='qicheleixing'){
							this.ruleForm.qicheleixing = obj[o];
							this.ro.qicheleixing = true;
							continue;
						}
						if(o=='pinpai'){
							this.ruleForm.pinpai = obj[o];
							this.ro.pinpai = true;
							continue;
						}
						if(o=='xinghao'){
							this.ruleForm.xinghao = obj[o];
							this.ro.xinghao = true;
							continue;
						}
						if(o=='nianfen'){
							this.ruleForm.nianfen = obj[o];
							this.ro.nianfen = true;
							continue;
						}
						if(o=='rizujin'){
							this.ruleForm.rizujin = obj[o];
							this.ro.rizujin = true;
							continue;
						}
						if(o=='qichetupian'){
							this.ruleForm.qichetupian = obj[o];
							this.ro.qichetupian = true;
							continue;
						}
						if(o=='zucheshijian'){
							this.ruleForm.zucheshijian = obj[o];
							this.ro.zucheshijian = true;
							continue;
						}
						if(o=='quxiaoshijian'){
							this.ruleForm.quxiaoshijian = obj[o];
							this.ro.quxiaoshijian = true;
							continue;
						}
						if(o=='quxiaoyuanyin'){
							this.ruleForm.quxiaoyuanyin = obj[o];
							this.ro.quxiaoyuanyin = true;
							continue;
						}
						if(o=='cheshangzhanghao'){
							this.ruleForm.cheshangzhanghao = obj[o];
							this.ro.cheshangzhanghao = true;
							continue;
						}
						if(o=='yonghuzhanghao'){
							this.ruleForm.yonghuzhanghao = obj[o];
							this.ro.yonghuzhanghao = true;
							continue;
						}
						if(o=='yonghuxingming'){
							this.ruleForm.yonghuxingming = obj[o];
							this.ro.yonghuxingming = true;
							continue;
						}
						if(o=='crossuserid'){
							this.ruleForm.crossuserid = obj[o];
							this.ro.crossuserid = true;
							continue;
						}
						if(o=='crossrefid'){
							this.ruleForm.crossrefid = obj[o];
							this.ro.crossrefid = true;
							continue;
						}
					}
				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(this.$storage.get("role")!="管理员") {
							this.ro.zucheshijian = true;
						}
						if(this.$storage.get("role")!="管理员") {
							this.ro.quxiaoshijian = true;
						}
						if(((json.yonghuzhanghao!=''&&json.yonghuzhanghao) || json.yonghuzhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.yonghuzhanghao = json.yonghuzhanghao
							this.ro.yonghuzhanghao = true;
						}
						if(((json.yonghuxingming!=''&&json.yonghuxingming) || json.yonghuxingming==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.yonghuxingming = json.yonghuxingming
							this.ro.yonghuxingming = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `dingdanquxiao/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.qichetupian!=null) {
						this.ruleForm.qichetupian = this.ruleForm.qichetupian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					//更新跨表属性
					var crossuserid;
					var crossrefid;
					var crossoptnum;
					var finishNum = 0;
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
									else {
										crossuserid=this.$storage.get('userid');
										crossrefid=obj['id'];
										crossoptnum=this.$storage.get('statusColumnName');
										crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
									}
								}
								if(crossrefid && crossuserid) {
									this.ruleForm.crossuserid = crossuserid;
									this.ruleForm.crossrefid = crossrefid;
									let params = { 
										page: 1, 
										limit: 10, 
										crossuserid:this.ruleForm.crossuserid,
										crossrefid:this.ruleForm.crossrefid,
									} 
									await this.$http({ 
										url: "dingdanquxiao/page", 
										method: "get", 
										params: params 
									}).then(({ 
										data 
									}) => { 
										if (data && data.code === 0) {
											finishNum = data.data.total
										}
									})
								}
								if(finishNum>=crossoptnum) {
									this.$message.error(this.$storage.get('tips'));
									return false;
								}
							}
							
							await this.$http({
								url: `dingdanquxiao/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.dingdanquxiaoCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.dingdanquxiaoCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
		}
	};
</script>
<style lang="css" scoped>
	.addEdit-block {
		padding: 30px;
	}
	.info-view {
		margin-bottom: 10px;
	}
	.info-row {
		display: flex;
		align-items: center;
		margin-bottom: 18px;
	}
	.info-label {
		width: 180px;
		color: #666;
		font-size: 15px;
		text-align: right;
		flex-shrink: 0;
	}
	.info-value {
		color: #333;
		font-size: 15px;
		margin-left: 16px;
		word-break: break-all;
	}
	.add-update-preview {
		padding: 40px 30px;
		background: none;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 49%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #6e6e6e;
		font-weight: 500;
		width: 180px;
		font-size: 15px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		padding: 0 10px;
		color: #666;
		background: none;
		font-weight: 500;
		display: inline-block;
		font-size: 15px;
		line-height: 40px;
		min-width: 100%;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 34px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 0px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: none;
		width: auto;
		font-size: 15px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		min-width: 100%;
		height: 120px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 0;
				cursor: not-allowed;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: none;
				width: auto;
				font-size: 15px;
				min-width: 400px;
				height: auto;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #1a3194;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #1fc3cb;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #32c67a;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #b3b3b3;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #156286;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
