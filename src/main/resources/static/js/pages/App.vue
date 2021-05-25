<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться через
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
            <product-list :products="products" />
        </div>
    </div>
</template>

<script>
    import ProductList from 'components/products/ProductList.vue'

    export default {
        components: {
            ProductList
        },
        data() {
            return {
                products: frontendData.products,
                profile: frontendData.profile
            }
        }
    }
</script>

<style>

</style>