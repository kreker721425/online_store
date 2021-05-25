<template>
    <div>
        <product-form :products="products" :productAttr="product" />
        <table>
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>price</th>
                <th>img</th>
                <th>description</th>
                <th>count</th>
                <th>manufacturer</th>
                <th>category</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
                <product-row v-for="product in products"
                         :key="product.id"
                         :product="product"
                         :editProduct="editProduct"
                         :deleteProduct="deleteProduct"
                         :products="products" />
            </tbody>
        </table>
    </div>
</template>

<script>
    import ProductRow from 'components/products/ProductRow.vue'
    import ProductForm from 'components/products/ProductForm.vue'

    export default {
        props: ['products'],
        components: {
            ProductRow,
            ProductForm
        },
        data() {
            return {
                product: null
            }
        },
        methods: {
            editProduct(product) {
              this.product = product
            },
            deleteProduct(product) {
                this.$resource('/products{/id}').remove({id: product.id}).then(result => {
                    if (result.ok) {
                        this.products.splice(this.products.indexOf(product), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>