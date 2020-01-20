<template>
  <div
    class="checkbox"
    :class="[typeClass, textColorClass, checkColorClass, { 'checkbox--has-errors': errors.lenght > 0 }]"
  >
    <input :id="isFor" :name="isFor" type="checkbox" class="checkbox__hidden">
    <label :for="isFor" class="checkbox__label" @click="changeValue">
      {{ text }}
      <slot></slot>
    </label>
  </div>
</template>

<script>
export default {
  props: {
    value: {
      type: Boolean,
      defaut: false
    },
    trueValue: {
      type: null,
      default: true
    },
    falseValue: {
      type: null,
      default: false
    },
    isFor: {
      type: String,
      default: ""
    },
    size: {
      type: String,
      default: "medium"
    },
    textColor: {
      type: String,
      default: "black"
    },
    checkColor: {
      type: String,
      default: "gray"
    },
    text: {
      type: String,
      default: ""
    },
    errors: {
      type: Array,
      default: () => []
    }
  },
  data: () => ({
    isChecked: false
  }),
  computed: {
    typeClass() {
      return `checkbox--${this.size}`;
    },
    textColorClass() {
      return `checkbox--text-${this.textColor}`;
    },
    checkColorClass() {
      return `checkbox--check-${this.checkColor}`;
    }
  },
  created() {
    this.isChecked = this.value === this.trueValue;
  },
  methods: {
    changeValue() {
      if (this.disabled) {
        return;
      }

      const isChecked = !this.isChecked;
      const val = isChecked ? this.trueValue : this.falseValue;
      this.$emit("input", val);
      this.isChecked = isChecked;
    }
  }
};
</script>

<style lang="scss">
.checkbox {
  display: flex;
  align-items: center;
}

.checkbox--has-errors {
  padding: 1rem;
  background-color: coral;
}

.checkbox__hidden {
  display: none;
}

.checkbox__label {
  position: relative;
  display: flex;
  align-items: center;
  overflow: hidden;

  &:hover {
    cursor: pointer;
  }

  &:before {
    content: "";
    background-repeat: no-repeat;
    background-position: center;
    background-size: 80%;
    border: 2px solid;
    transition: background-image 200ms;
  }

  &:after {
    content: "";
    position: absolute;
    top: 50%;
    background-image: url("data:image/svg+xml,%3Csvg width='30' height='28' viewBox='0 0 30 28' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M11.034 27.39L0 16.842l3.3-3.424 7.27 6.95L26.279.8 30 3.764z' fill='%2344B1FA' fill-rule='evenodd'/%3E%3C/svg%3E");
    background-repeat: no-repeat;
    background-size: contain;
    transform: translateY(-50%) translateX(-2rem);
    opacity: 0;
    transition: transform 120ms linear, opacity 120ms linear;
  }
}

.checkbox--text-gray .checkbox__hidden + .checkbox__label {
  color: color(disabled-item);
}

.checkbox--medium .checkbox__hidden + .checkbox__label {
  &:before {
    width: 1.25rem;
    height: 1.25rem;
  }

  &:after {
    left: 0.238rem;
    width: 0.8rem;
    height: 0.8rem;
  }
}

.checkbox__hidden:checked + .checkbox__label {
  &::before {
    border-color: color(enabled-item);
  }

  &:after {
    opacity: 1;
    transform: translateY(-50%) translateX(0);
  }
}

.checkbox--has-errors .checkbox__hidden + .checkbox__label {
  color: color(text-red);

  &:before {
    border-color: color(text-red);
  }
}
</style>
