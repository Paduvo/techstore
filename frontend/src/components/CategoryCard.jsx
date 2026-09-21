function CategoryCard({ icon, title }) {
  return (
    <div className="category-card">
      <div className="category-icon">
        {icon}
      </div>

      <div>
        <h3>{title}</h3>
        <a href="#produtos">
          Explorar produtos →
        </a>
      </div>
    </div>
  );
}

export default CategoryCard;