<template>
	<div class="home-content">
		<!-- title -->
		<div id="home-title" class="home-title animate__animated">
			<div class="titles" >
				<span>欢迎使用</span>
				{{this.$project.projectName}}
			</div>
		</div>
		<!-- statis -->
		<div class="statis-box">
			<div id="statis1" class="statis1 animate__animated" v-if="isAuth('qichechuzu','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-car stats-icon-fix"></span>
				</div>
				<div class="right">
					<div class="num">{{qichechuzuCount}}</div>
					<div class="name">汽车租赁总数</div>
				</div>
			</div>
			<div id="statis2" class="statis2 animate__animated" v-if="isAuth('zulindingdan','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-order stats-icon-fix"></span>
				</div>
				<div class="right">
					<div class="num">{{zulindingdanCount}}</div>
					<div class="name">租赁订单总数</div>
				</div>
			</div>
			<div id="statis3" class="statis3 animate__animated" v-if="isAuth('guzhangshangbao','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-warning stats-icon-fix"></span>
				</div>
				<div class="right">
					<div class="num">{{guzhangshangbaoCount}}</div>
					<div class="name">故障上报总数</div>
				</div>
			</div>
			<div id="statis4" class="statis4 animate__animated" v-if="isAuth('shigushangbao','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-bianji stats-icon-fix"></span>
				</div>
				<div class="right">
					<div class="num">{{shigushangbaoCount}}</div>
					<div class="name">事故上报总数</div>
				</div>
			</div>
		</div>
		<!-- statis -->
		<div id="news-box" class="news-box animate__animated">
			<div class="news-title">
				新闻资讯
			</div>
			<div class="news-list">
				<div class="news-item" v-for="(item,index) in newsList" :index="index" @click="newsDetailClick(item)">
					<div class="news-text">
						{{item.title}}
					</div>
					<div class="news-time">
						{{item.addtime}}
					</div>
				</div>
			</div>
		</div>
		<el-dialog :visible.sync="newsVisible" title="新闻资讯" :append-to-body="true" width="60%">
			<div style="width: 100%;text-align: center;font-size: 20px;color: #000;padding: 0 0 20px;font-weight: bold;">{{newsDetail.title}}</div>
			<div style="width: 100%;">
				<div class="ql-snow ql-editor" v-html="newsDetail.content"></div>
			</div>
		</el-dialog>
	
		<!-- echarts -->
		<!-- 4 -->
		<div class="type4">
			<div id="qichechuzuChart1" class="echarts1 animate__animated" v-if="isAuth('qichechuzu','首页统计')"></div>
			<div id="zulindingdanChart1" class="echarts2 animate__animated" v-if="isAuth('zulindingdan','首页统计')"></div>
			<div id="guzhangshangbaoChart1" class="echarts3 animate__animated" v-if="isAuth('guzhangshangbao','首页统计')"></div>
			<div id="shigushangbaoChart1" class="echarts4 animate__animated" v-if="isAuth('shigushangbao','首页统计')"></div>
		</div>
	</div>
</template>
<script>
import 'animate.css'
//4
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
	data() {
		return {
			qichechuzuCount: 0,
			zulindingdanCount: 0,
			guzhangshangbaoCount: 0,
			shigushangbaoCount: 0,
			line: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":15,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":4,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#1a3194","#91cc75","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"#333","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"showSymbol":true,"symbol":"emptyCircle","symbolSize":4},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"}},
			bar: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":12,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":4,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#1a3194","#ecbb45","#ecc872","#da9d0e","#a48130","#6e80ca","#4d64ca","#2b386f"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"#333","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"barWidth":"auto","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"colorBy":"data","barCategoryGap":"20%"},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"base":{"animate":false,"interval":2000}},
			pie: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#1a3194","#ecbb45","#ecc872","#da9d0e","#a48130","#6e80ca","#4d64ca","#2b386f"],"title":{"borderType":"solid","padding":[5,0,0,0],"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":14,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":[5,0,0,0],"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":0,"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#666","color":"inherit","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#666","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
			funnel: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#1a3194","#ecbb45","#ecc872","#da9d0e","#a48130","#6e80ca","#4d64ca","#2b386f"],"title":{"borderType":"solid","padding":2,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"center","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#666","textShadowColor":"transparent","fontSize":14,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"#ccc","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":5,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"orient":"vertical","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"left","borderWidth":0,"width":"auto","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":20,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#000","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#fff","color":"","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#fff","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
			boardBase: {"funnelNum":8,"lineNum":8,"gaugeNum":8,"barNum":8,"pieNum":8},
			gauge: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#1a3194","#ecbb45","#ecc872","#da9d0e","#a48130","#6e80ca","#4d64ca","#2b386f"],"title":{"top":"top","left":"left","textStyle":{"fontSize":14,"lineHeight":24,"color":"#333","fontWeight":600}},"series":{"pointer":{"offsetCenter":[0,"10%"],"icon":"path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z","width":8,"length":"80%"},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"opacity":0.5,"shadowBlur":1,"shadowColor":"#000"},"roundCap":true},"anchor":{"show":true,"itemStyle":{"color":"inherit"},"size":18,"showAbove":true},"emphasis":{"disabled":false},"progress":{"show":true,"roundCap":true,"overlap":true},"splitNumber":25,"detail":{"formatter":"{value}","backgroundColor":"inherit","color":"#fff","borderRadius":3,"width":20,"fontSize":12,"height":10},"title":{"fontSize":14},"animation":true}},
			newsList: [],
			newsDetail: {},
			newsVisible: false,
		};
	},
	mounted(){
		this.init();
		this.getqichechuzuCount();
		if(this.isAuth('qichechuzu','首页统计')){
			this.qichechuzuChat1();
		}
		this.getzulindingdanCount();
		if(this.isAuth('zulindingdan','首页统计')){
			this.zulindingdanChat1();
		}
		this.getguzhangshangbaoCount();
		if(this.isAuth('guzhangshangbao','首页统计')){
			this.guzhangshangbaoChat1();
		}
		this.getshigushangbaoCount();
		if(this.isAuth('shigushangbao','首页统计')){
			this.shigushangbaoChat1();
		}
		window.addEventListener('scroll', this.handleScroll)
		setTimeout(()=>{
			this.handleScroll()
		},100)
	},
	computed: {
		avatar(){
			return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
		},
	},
	methods:{
		handleScroll() {
			let arr = [
				{id:'home-title',css:'animate__'},
				{id:'statis1',css:'animate__'},
				{id:'statis2',css:'animate__'},
				{id:'statis3',css:'animate__'},
				{id:'statis4',css:'animate__'},
				{id:'news-box',css:'animate__'},
				{id:'qichechuzuChart1',css:'animate__'},
				{id:'zulindingdanChart1',css:'animate__'},
				{id:'guzhangshangbaoChart1',css:'animate__'},
				{id:'shigushangbaoChart1',css:'animate__'},
			]
			
			for (let i in arr) {
				let doc = document.getElementById(arr[i].id)
				if (doc) {
					let top = doc.offsetTop
					let win_top = window.innerHeight + window.pageYOffset
					// console.log(top,win_top)
					if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
						// console.log(doc)
						doc.classList.add(arr[i].css)
					}
				}
			}
		},
		// 统计图动画
		myChartInterval(type, xAxisData, seriesData, myChart) {
			this.$nextTick(() => {
				setInterval(() => {
					let xAxis = xAxisData.shift()
					xAxisData.push(xAxis)
					let series = seriesData.shift()
					seriesData.push(series)
				
					if (type == 1) {
						myChart.setOption({
							xAxis: [{
								data: xAxisData
							}],
							series: [{
								data: seriesData
							}]
						});
					}
					if (type == 2) {
						myChart.setOption({
							yAxis: [{
								data: xAxisData
							}],
							series: [{
								data: seriesData
							}]
						});
					}
				}, $template2.back.board.bar.base.interval);
			})
		},
		init(){
			if(this.$storage.get('Token')){
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code != 0) {
						router.push({ name: 'login' })
					}
				});
			}else{
				router.push({ name: 'login' })
			}
			this.getNewsList()
		},
		getNewsList(){
			let params = {
				page: 1,
				limit: 7,
				sort: 'addtime',
				order: 'desc',
			}
			this.$http({
				url: `news/list`,
				method: "get",
				params: params
			}).then(res => {
				if(res.data&&res.data.code==0){
					this.newsList = res.data.data.list
				}
			});
		},
		newsDetailClick(row){
			this.newsDetail = row
			this.newsVisible = true
		},
		getqichechuzuCount() {
			this.$http({
				url: `qichechuzu/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.qichechuzuCount = data.data
				}
			})
		},
// 1234 漏
		qichechuzuChat1() {
			this.$nextTick(()=>{

				var qichechuzuChart1 = echarts.init(document.getElementById("qichechuzuChart1"),'macarons');
				this.$http({
					url: "qichechuzu/group/qicheleixing",
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data;
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.funnelNum){
								break;
							}
							xAxis.push(res[i].qicheleixing);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].qicheleixing
							})
						}
						var option = {};
						let titleObj = this.funnel.title
						titleObj.text = '汽车类型统计'
						
						let legendObj = {
							data: xAxis,
						}
						legendObj = Object.assign(legendObj , this.funnel.legend)
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.funnel.tooltip?this.funnel.tooltip:{})
						let seriesObj = {
							name: '汽车类型统计',
							data: pArray,
							type: 'funnel',
							left: '10%',
							top: 60,
							bottom: 60,
							width: '80%',
							minSize: '0%',
							maxSize: '100%',
						}
						seriesObj = Object.assign(seriesObj , this.funnel.series)
						const gridObj = this.funnel.grid
						option = {
							backgroundColor: this.funnel.backgroundColor,
							color: this.funnel.color,
							title: titleObj,
							legend: legendObj,
							tooltip: tooltipObj,
							series: seriesObj,
							grid: gridObj
						}
						// 使用刚指定的配置项和数据显示图表。
						qichechuzuChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							qichechuzuChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


		getzulindingdanCount() {
			this.$http({
				url: `zulindingdan/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.zulindingdanCount = data.data
				}
			})
		},
// 1234 竖
		zulindingdanChat1() {
			this.$nextTick(()=>{

				var zulindingdanChart1 = echarts.init(document.getElementById("zulindingdanChart1"),'macarons');
				this.$http({
					url: "zulindingdan/group/qicheleixing",
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data;
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.barNum){
								break;
							}
							xAxis.push(res[i].qicheleixing);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].qicheleixing
							})
						}
						var option = {};
						let titleObj = this.bar.title
						titleObj.text = '租赁次数统计'
						
						const legendObj = this.bar.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.bar.tooltip?this.bar.tooltip:{})
				
						let xAxisObj = this.bar.xAxis
						xAxisObj.type = 'category'
						xAxisObj.data = xAxis
						
						let yAxisObj = this.bar.yAxis
						yAxisObj.type = 'value'
						let seriesObj = {
							data: yAxis,
							type: 'bar',
						}
						seriesObj = Object.assign(seriesObj , this.bar.series)
						const gridObj = this.bar.grid
						
						option = {
							backgroundColor: this.bar.backgroundColor,
							color: this.bar.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							xAxis: xAxisObj,
							yAxis: yAxisObj,
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						zulindingdanChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							zulindingdanChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


		getguzhangshangbaoCount() {
			this.$http({
				url: `guzhangshangbao/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.guzhangshangbaoCount = data.data
				}
			})
		},
// 1234 饼3
		guzhangshangbaoChat1() {
			this.$nextTick(()=>{

				var guzhangshangbaoChart1 = echarts.init(document.getElementById("guzhangshangbaoChart1"),'macarons');
				this.$http({
					url: "guzhangshangbao/group/guzhangleixing",
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data;
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.pieNum){
								break;
							}
							xAxis.push(res[i].guzhangleixing);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].guzhangleixing
							})
						}
						var option = {};
						let titleObj = this.pie.title
						titleObj.text = '故障类型统计'
						
						const legendObj = this.pie.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c} ({d}%)'}
						tooltipObj = Object.assign(tooltipObj , this.pie.tooltip?this.pie.tooltip:{})
						
						let seriesObj = {
							type: 'pie',
							radius: ['25%', '55%'],
							roseType: 'area',
							center: ['50%', '60%'],
							data: pArray,
							emphasis: {
								itemStyle: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
						seriesObj = Object.assign(seriesObj , this.pie.series)
						const gridObj = this.pie.grid
						option = {
							backgroundColor: this.pie.backgroundColor,
							color: this.pie.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						guzhangshangbaoChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							guzhangshangbaoChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


		getshigushangbaoCount() {
			this.$http({
				url: `shigushangbao/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.shigushangbaoCount = data.data
				}
			})
		},
// 1234 饼2
		shigushangbaoChat1() {
			this.$nextTick(()=>{

				var shigushangbaoChart1 = echarts.init(document.getElementById("shigushangbaoChart1"),'macarons');
				this.$http({
					url: "shigushangbao/group/shiguleixing",
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data;
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.pieNum){
								break;
							}
							xAxis.push(res[i].shiguleixing);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].shiguleixing
							})
						}
						var option = {};
						let titleObj = this.pie.title
						titleObj.text = '事故类型统计'
						
						const legendObj = this.pie.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c} ({d}%)'}
						tooltipObj = Object.assign(tooltipObj , this.pie.tooltip?this.pie.tooltip:{})
						
						let seriesObj = {
							type: 'pie',
							radius: ['25%', '55%'],
							center: ['50%', '60%'],
							data: pArray,
							emphasis: {
								itemStyle: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
						seriesObj = Object.assign(seriesObj , this.pie.series)
						const gridObj = this.pie.grid
						option = {
							backgroundColor: this.pie.backgroundColor,
							color: this.pie.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						shigushangbaoChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							shigushangbaoChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


	}
};
</script>
<style scoped>
	.home-content {
		padding: 10px 30px;
		background: url(http://codegen.caihongy.cn/20240925/11b8d38a91c245019f8d4aba3f559743.png) no-repeat center top / cover;
		display: flex;
		width: 100%;
		min-height: 100vh;
		justify-content: flex-start;
		align-items: flex-start;
		flex-wrap: wrap;
	}
	.home-content .home-title {
		border-radius: 5px;
		padding: 10px 0;
		box-shadow: 0 0px 0px rgba(0,0,0,.3);
		margin: 10px 0;
		background: none;
		display: flex;
		width: 100%;
		justify-content: center;
		align-items: center;
		transition: 0.3s;
	}
	.home-content .home-title .titles {
		padding: 0 0 0 12px;
		color: #1a3194;
		font-weight: 600;
		font-size: 24px;
		line-height: 44px;
	}
	.home-content .home-title:hover {
		transform: translate3d(0, 0px, 0);
		z-index: 1;
		background: rgba(255,255,255,.12);
	}
	.home-content .statis-box {
		padding: 0;
		margin: 20px auto;
		display: flex;
		width: calc(100% - 40px);
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
		gap: 20px;
	}
	.home-content .statis-box .statis1, 
	.home-content .statis-box .statis2, 
	.home-content .statis-box .statis3, 
	.home-content .statis-box .statis4 {
		border: 0;
		border-radius: 16px;
		padding: 24px;
		margin: 0;
		width: calc(25% - 40px);
		min-width: 240px;
		height: 120px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		transition: all 0.35s ease;
		box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
		position: relative;
		overflow: hidden;
	}
	.home-content .statis-box .statis1::before, 
	.home-content .statis-box .statis2::before, 
	.home-content .statis-box .statis3::before, 
	.home-content .statis-box .statis4::before {
		content: '';
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		background: rgba(255, 255, 255, 0.1);
		opacity: 0;
		transition: opacity 0.3s ease;
		z-index: 1;
	}
	.home-content .statis-box .statis1:hover,
	.home-content .statis-box .statis2:hover,
	.home-content .statis-box .statis3:hover,
	.home-content .statis-box .statis4:hover {
		transform: translateY(-8px);
		box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
	}
	.home-content .statis-box .statis1:hover::before,
	.home-content .statis-box .statis2:hover::before,
	.home-content .statis-box .statis3:hover::before,
	.home-content .statis-box .statis4:hover::before {
		opacity: 1;
	}
	.home-content .statis-box .statis1:hover .left,
	.home-content .statis-box .statis2:hover .left,
	.home-content .statis-box .statis3:hover .left,
	.home-content .statis-box .statis4:hover .left {
		transform: scale(1.05);
	}
	.home-content .statis-box .statis1:hover .right .num,
	.home-content .statis-box .statis2:hover .right .num,
	.home-content .statis-box .statis3:hover .right .num,
	.home-content .statis-box .statis4:hover .right .num {
		transform: scale(1.08);
	}
	.home-content .statis-box .statis1 .left,	.home-content .statis-box .statis2 .left,	.home-content .statis-box .statis3 .left,	.home-content .statis-box .statis4 .left {		width: 80px;		height: 80px;		border-radius: 50%;		display: flex;		justify-content: center;		align-items: center;		transition: transform 0.3s ease;		position: relative;		z-index: 2;		padding: 0;		margin: 0;		overflow: visible;	}
	.home-content .statis-box .statis1 .left::after,
	.home-content .statis-box .statis2 .left::after,
	.home-content .statis-box .statis3 .left::after,
	.home-content .statis-box .statis4 .left::after {
		content: '';
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		border-radius: 50%;
		background: rgba(255, 255, 255, 0.15);
		z-index: 1;
	}
	.home-content .statis-box .statis1 .left .iconfont,
	.home-content .statis-box .statis2 .left .iconfont,
	.home-content .statis-box .statis3 .left .iconfont,
	.home-content .statis-box .statis4 .left .iconfont {
		display: block;
		font-size: 36px;
		color: #fff;
		z-index: 2;
	}
	.home-content .statis-box .statis1 .right,
	.home-content .statis-box .statis2 .right,
	.home-content .statis-box .statis3 .right,
	.home-content .statis-box .statis4 .right {
		flex-direction: column;
		display: flex;
		width: calc(100% - 100px);
		justify-content: center;
		z-index: 2;
	}
	.home-content .statis-box .statis1 .right .num,
	.home-content .statis-box .statis2 .right .num,
	.home-content .statis-box .statis3 .right .num,
	.home-content .statis-box .statis4 .right .num {
		margin: 0 0 10px;
		color: #fff;
		font-weight: 700;
		font-size: 32px;
		line-height: 1;
		transition: transform 0.3s ease;
	}
	.home-content .statis-box .statis1 .right .name,
	.home-content .statis-box .statis2 .right .name,
	.home-content .statis-box .statis3 .right .name,
	.home-content .statis-box .statis4 .right .name {
		margin: 0;
		color: rgba(255, 255, 255, 0.85);
		font-size: 16px;
		font-weight: 500;
		line-height: 1.2;
	}
	.home-content .statis-box .statis1 {
		background: linear-gradient(135deg, #3a7bd5, #1a3194);
	}
	.home-content .statis-box .statis1 .left {
		background: rgba(255, 255, 255, 0.1);
	}
	.home-content .statis-box .statis1 .left .iconfont {
		color: #fff;
	}
	.home-content .statis-box .statis2 {
		background: linear-gradient(135deg, #5d9cec, #237AFA);
	}
	.home-content .statis-box .statis2 .left {
		background: rgba(255, 255, 255, 0.1);
	}
	.home-content .statis-box .statis2 .left .iconfont {
		color: #fff;
	}
	.home-content .statis-box .statis3 {
		background: linear-gradient(135deg, #ff7676, #fa6161);
	}
	.home-content .statis-box .statis3 .left {
		background: rgba(255, 255, 255, 0.1);
	}
	.home-content .statis-box .statis3 .left .iconfont {
		color: #fff;
	}
	.home-content .statis-box .statis4 {
		background: linear-gradient(135deg, #2ecc71, #24BF74);
	}
	.home-content .statis-box .statis4 .left {
		background: rgba(255, 255, 255, 0.1);
	}
	.home-content .statis-box .statis4 .left .iconfont {
		color: #fff;
	}
	.home-content .news-box {
		background: linear-gradient(135deg, #f8fafc 0%, #e0e7ef 100%);
		border-radius: 18px;
		box-shadow: 0 4px 24px rgba(60, 100, 180, 0.08);
		padding: 18px 12px 12px 12px;
		margin: 0 0 18px 0;
		width: 100%;
		min-width: 0;
		max-width: none;
		display: flex;
		flex-direction: column;
		align-items: stretch;
		transition: box-shadow 0.2s, transform 0.2s;
	}
	.home-content .news-box .news-title {
		font-size: 20px;
		font-weight: 700;
		color: #2d3a4b;
		margin-bottom: 10px;
		letter-spacing: 2px;
		text-align: left;
		background: none;
		padding: 0;
		border: none;
		line-height: 1.2;
	}
	.home-content .news-box .news-list {
		display: flex;
		flex-wrap: wrap;
		gap: 16px 2%;
		width: 100%;
	}
	.home-content .news-box .news-item {
		background: #fff;
		border-radius: 10px;
		box-shadow: 0 2px 8px rgba(60, 100, 180, 0.06);
		padding: 10px 14px 8px 14px;
		display: flex;
		flex-direction: column;
		transition: box-shadow 0.2s, transform 0.2s;
		cursor: pointer;
		border: none;
		margin: 0;
		width: 31.33%;
		min-width: 220px;
		box-sizing: border-box;
	}
	.home-content .news-box .news-item:hover {
		box-shadow: 0 6px 18px rgba(60, 100, 180, 0.13);
		transform: translateY(-2px) scale(1.02);
	}
	.home-content .news-box .news-item .news-text {
		font-size: 15px;
		color: #22304a;
		font-weight: 500;
		margin-bottom: 4px;
		word-break: break-all;
		white-space: normal;
		text-overflow: initial;
	}
	.home-content .news-box .news-item .news-time {
		font-size: 12px;
		color: #8a99b3;
		text-align: right;
		letter-spacing: 1px;
		display: block;
		margin-top: 0;
	}
	@media (max-width: 900px) {
		.home-content .news-box .news-item {
			width: 48%;
			min-width: 0;
		}
	}
	@media (max-width: 600px) {
		.home-content .news-box .news-item {
			width: 100%;
		}
	}
	.home-content .type4 {
		padding: 0;
		align-content: flex-start;
		background: none;
		display: flex;
		width: 100%;
		justify-content: space-between;
		flex-wrap: wrap;
		height: auto;
	}
	.home-content .type4 .echarts1 {
		border: 0px solid #ccc;
		border-radius: 10px;
		padding: 20px;
		margin: 10px;
		background: rgba(255,255,255,1);
		width: calc(50% - 20px);
		transition: 0.3s;
		height: 400px;
	}
	.home-content .type4 .echarts1:hover {
		transform: translate3d(0, 0px, 0);
		z-index: 1;
		background: rgba(255,255,255,1);
	}
	.home-content .type4 .echarts2 {
		border: 0px solid #ccc;
		border-radius: 10px;
		padding: 20px;
		margin: 10px;
		background: rgba(255,255,255,1);
		width: calc(50% - 20px);
		transition: 0.3s;
		height: 400px;
	}
	.home-content .type4 .echarts2:hover {
		transform: translate3d(0, 0px, 0);
		z-index: 1;
		background: rgba(255,255,255,1);
	}
	.home-content .type4 .echarts3 {
		border: 0px solid #ccc;
		border-radius: 10px;
		padding: 20px;
		margin: 10px;
		background: rgba(255,255,255,1);
		width: calc(50% - 20px);
		transition: 0.3s;
		height: 280px;
	}
	.home-content .type4 .echarts3:hover {
		transform: translate3d(0, 0px, 0);
		z-index: 1;
		background: rgba(255,255,255,1);
	}
	.home-content .type4 .echarts4 {
		border: 0px solid #ccc;
		border-radius: 10px;
		padding: 20px;
		margin: 10px;
		background: rgba(255,255,255,1);
		width: calc(50% - 20px);
		transition: 0.3s;
		height: 280px;
	}
	.home-content .type4 .echarts4:hover {
		transform: translate3d(0, 0px, 0);
		z-index: 1;
		background: rgba(255,255,255,1);
	}
	.echarts-flag-2 {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		padding: 10px 20px;
		background: rebeccapurple;
	}
	.echarts-flag-2>div {
		width: 32%;
		height: 300px;
		margin: 10px 0;
		background: rgba(255,255,255,.1);
		border-radius: 8px;
		padding: 10px 20px;
	}
	.animate__animated {
		animation-fill-mode: none;
	}
</style>
