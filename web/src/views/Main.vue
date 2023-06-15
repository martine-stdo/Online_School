<template>
  <div>
    <el-carousel :interval="4000" type="card" height="240px">
      <el-carousel-item v-for="item in 6" :key="item">
        <el-skeleton animated>
          <template slot="template">
            <el-skeleton-item
              variant="image"
              style="width: 100%; height: 240px"
            />
          </template>
        </el-skeleton>
      </el-carousel-item>
    </el-carousel>
    <span>
      <h2>所有课程</h2>
      <el-divider></el-divider>
    </span>
    <div class="card-layout">
      <el-card
        class="card"
        v-for="x in 8"
        :key="x"
        :body-style="{ padding: '0px' }"
      >
        <el-skeleton animated>
          <template slot="template">
            <el-skeleton-item variant="image" style="height: 180px" />
          </template>
          <template>
            <div style="height: 180px">课程名称</div>
          </template>
        </el-skeleton>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "Main",
  created() {},
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
.card-layout {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;

  margin-left: -0.25rem;
  margin-right: -0.25rem;
}

.card {
  flex-basis: 24%;
  margin-bottom: 15px;
}
</style>