<template>
  <div class="container mt-3">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <!-- TODO: 수정 시작 #1 -->
        <input
          type="text"
          class="form-control"
          placeholder="Search by Email"
          v-model="searchTitle"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="
              page = 1;
              retrieveTutorials();
            "
          >
            Search
          </button>
        </div>
        <!-- TODO: 수정 끝 #1 -->
      </div>
    </div>

    <div class="'col-md-12">
      <div class="mb-3">
        Items per Page:
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <option v-for="size in pageSize" :key="size" :value="size">
            {{ size }}
          </option>
        </select>
      </div>

      <!-- TODO: page bar 추가 -->
      <b-pagination
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        prev-text="Prev"
        next-text="Next"
        @change="handlePageChange"
      >
      </b-pagination>
    </div>

    <table class="table">
      <thead>
        <tr>
          <th scope="col">First Name</th>
          <th scope="col">Last Name</th>
          <th scope="col">Email</th>
          <th scope="col">Phone</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <!--      조회 데이터 생성 부분-->
      <tbody v-for="(customer, index) in customers" :key="index">
        <tr>
          <td>{{ customer.firstName }}</td>
          <td>{{ customer.lastName }}</td>
          <td>{{ customer.email }}</td>
          <td>{{ customer.phone }}</td>
          <td>
            <a :href="'/customers/' + customer.id" class="btn btn-primary">
              Edit
            </a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import CustomerDataService from "@/services/CustomerDataService";

/* eslint-disable */
export default {
  name: "customers",
  data() {
    return {
      customers: [],
    };
  },
  methods: {
    // 모든 회원 조회 서비스 호출
    retrieveCustomers() {
      // axios로 spring에 모든 회원 조회 요청
      CustomerDataService.getAll()
        //  성공하면 then으로 서버(spring) 데이터(response.data)가 들어옴
        .then((response) => {
          this.customers = response.data;
        })
        //  실패하면 catch로 에러메세지가 들어옴
        .catch((e) => {
          alert(e);
        });
    },
  },
  //화면이 뜨자마자 실행되는 이벤트(모든 회원조회가 실행됨)
  mounted() {
    this.retrieveCustomers();
  },
};
</script>
