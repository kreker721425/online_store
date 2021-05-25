<template>
    <div>
        <table>
            <tr>
                <td>
                    id
                </td>
                <td>
                    <input type="text" placeholder="Write id" v-model="id" /></span>
                </td>
            </tr>
            <tr>
                <td>
                    name
                </td>
                <td>
                    <input type="text" placeholder="Write name" v-model="name" />
                </td>
            </tr>
            <tr>
                <td>
                    price
                </td>
                <td>
                    <input type="text" placeholder="Write price" v-model="price" />
                </td>
            </tr>
            <tr>
                <td>
                    img
                </td>
                <td>
                    <input type="text" placeholder="Write img" v-model="img" />
                </td>
            </tr>
            <tr>
                <td>
                    description
                </td>
                <td>
                    <input type="text" placeholder="Write description" v-model="description" />
                </td>
            </tr>
            <tr>
                <td>
                    count
                </td>
                <td>
                    <input type="text" placeholder="Write count" v-model="count" />
                </td>
            </tr>
            <tr>
                <td>
                    manufacturer
                </td>
                <td>
                    <input type="text" placeholder="Write manufacturer" v-model="manufacturer" />
                </td>
            </tr>
            <tr>
                <td>
                    category
                </td>
                <td>
                    <input type="text" placeholder="Write category" v-model="category" />
                </td>
            </tr>
        </table>
        <div>
            <span><input type="button" value="Сохранить" @click="save" /></span>
        </div>
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++ ) {
            if (list[i].id === id) {
                return i
            }
        }

        return -1
    }

    export default {
        props: ['products', 'productAttr'],
        data: function() {
            return {
                id: '',
                name: '',
                price: '',
                img: '',
                description: '',
                count: '',
                manufacturer: '',
                category: '',
                id: ''
            }
        },
        watch: {
            productAttr: function(newVal, oldVal) {
                this.id = newVal.id
                this.name = newVal.name
                this.price = newVal.price
                this.img = newVal.img
                this.description = newVal.description
                this.count = newVal.count
                this.manufacturer = newVal.manufacturer
                this.category = newVal.category
            }
        },
        methods: {
            save() {
                const product = { id: this.id,
                    name: this.name,
                    price: this.price,
                    img: this.img,
                    description: this.description,
                    count: this.count,
                    manufacturer: this.manufacturer,
                    category: this.category,
                    };

                if (this.id) {
                    this.$resource('/products{/id}').update({id: this.id}, product).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.products, data.id);
                            this.products.splice(index, 1, data);
                            this.id = ''
                            this.name = ''
                            this.price = ''
                            this.img = ''
                            this.description = ''
                            this.count = ''
                            this.manufacturer = ''
                            this.category = ''
                        })
                    )
                } else {
                    this.$resource('/products{/id}').save({}, product).then(result =>
                        result.json().then(data => {
                            this.products.push(data);
                            this.id = ''
                            this.name = ''
                            this.price = ''
                            this.img = ''
                            this.description = ''
                            this.count = ''
                            this.manufacturer = ''
                            this.category = ''
                        })
                    )
                }
            }
        }
    }
</script>

<style>

</style>